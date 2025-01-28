package com.example.aliaimageloader.data

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.security.MessageDigest

class DiskCache(private val context: Context, private val directoryName: String = "image_cache") {
    private val cacheDir: File by lazy {
        File(context.cacheDir, directoryName).apply { mkdirs() }
    }

    private fun hashKey(key: String): String {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(key.toByteArray())
        return digest.digest().joinToString("") { "%02x".format(it) }
    }

    fun getFile(key: String): File? {
        val hashedKey = hashKey(key)
        return File(cacheDir, hashedKey).takeIf { it.exists() }
    }

    fun saveFile(key: String, inputStream: InputStream) {
        val hashedKey = hashKey(key)
        val file = File(cacheDir, hashedKey)
        inputStream.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
    }
}