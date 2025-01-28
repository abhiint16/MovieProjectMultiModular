package com.example.logging

import com.example.logging.logappender.ConsoleAppender
import com.example.logging.logappender.LogAppender

object Logger {

    private var config: LoggerConfig = LoggerConfig(LogLevel.INFO, ConsoleAppender())

    fun setConfig(config: LoggerConfig) {
        this.config = config
    }

    fun log(level: LogLevel, message: String, logAppender: LogAppender? = null) {
        if (level.ordinal >= config.logLevel.ordinal) {
            val logMessage = LogMessage(level, message)
            logAppender?.let {
                logAppender.append(logMessage)
            } ?: config.logAppender.append(logMessage)
        }
    }

    fun debug(message: String) {
        log(LogLevel.DEBUG, message)
    }

    fun info(message: String) {
        log(LogLevel.INFO, message)
    }

    fun warning(message: String) {
        log(LogLevel.WARNING, message)
    }

    fun error(message: String) {
        log(LogLevel.ERROR, message)
    }

    fun fatal(message: String) {
        log(LogLevel.FATAL, message)
    }
}