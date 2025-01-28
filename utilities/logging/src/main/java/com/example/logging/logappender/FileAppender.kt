package com.example.logging.logappender

import com.example.logging.LogMessage
import java.io.FileWriter
import java.io.IOException

class FileAppender(private val filePath: String) : LogAppender {
    override fun append(logMessage: LogMessage) {
        try {
            FileWriter(filePath, true).use { writer ->
                writer.write(logMessage.toString() + "\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}