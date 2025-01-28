package com.example.logging.logappender

import com.example.logging.LogMessage

class ConsoleAppender : LogAppender {
    override fun append(logMessage: LogMessage) {
        println(logMessage)
    }
}