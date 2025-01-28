package com.example.aliaimageloader.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream

class ImageDecoder {
    fun decode(inputStream: InputStream): Bitmap {
        return BitmapFactory.decodeStream(inputStream)
    }
}