package com.example.logging.logappender

import com.example.logging.LogMessage

interface LogAppender {
    fun append(logMessage: LogMessage)
}