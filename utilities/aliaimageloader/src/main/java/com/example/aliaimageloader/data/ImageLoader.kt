package com.example.aliaimageloader.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class ImageLoader(
    private val context: Context,
    private val memoryCache: MemoryCache = MemoryCache(),
    private val diskCache: DiskCache = DiskCache(context),
    private val imageDecoder: ImageDecoder = ImageDecoder()
) {

    suspend fun load(url: String): Bitmap? {

        // Check Memory Cache
        memoryCache.get(url)?.let { return it }

        // Check Disk Cache
//        diskCache.getFile(url)?.let { file ->
//            return BitmapFactory.decodeFile(file.path).also {  memoryCache.put(url, it)}
////            return imageDecoder.decode(file.inputStream()).also { memoryCache.put(url, it) }
//        }

        // Download from Network
        return downloadAndCache(url)
    }

    private suspend fun downloadAndCache(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                if (connection.responseCode == 200) {
                    connection.inputStream.use { input ->
                        val bitmap = input?.let { imageDecoder.decode(it) }
                        bitmap?.let {
                            memoryCache.put(url, it)
                            diskCache.saveFile(url, input)
                        }
                        bitmap
                    }
                } else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}