package com.example.filedownloader

import com.example.common.abstraction.FileDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

class FileDownloaderImpl : FileDownloader {
    override suspend fun downloadResource(downloadUrl: String, destPath: String) {
        withContext(Dispatchers.IO) {
            try {
                val destinationFile = File(destPath)
                var finalDownloadFile: File? = null
                if (destinationFile.isDirectory) {
                    val size = destinationFile.listFiles()?.size
                    finalDownloadFile = File(destinationFile, "${size?.plus(1)}.jpg")
                    finalDownloadFile.createNewFile()
                }
                val connection = URL(downloadUrl).openConnection() as HttpURLConnection
                connection.inputStream.use { input ->
                    finalDownloadFile?.let { file ->
                        file.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
}