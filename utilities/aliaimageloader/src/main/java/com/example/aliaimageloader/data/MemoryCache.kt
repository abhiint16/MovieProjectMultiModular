package com.example.aliaimageloader.data

import android.graphics.Bitmap
import androidx.collection.LruCache

class MemoryCache(private val maxSize: Int = (Runtime.getRuntime().maxMemory() / 8).toInt()) {
    private val cache = LruCache<String, Bitmap>(maxSize)

    fun put(key: String, bitmap: Bitmap) {
        cache.put(key, bitmap)
    }

    fun get(key: String): Bitmap? {
        return cache.get(key)
    }

    fun clear() {
        cache.evictAll()
    }
}