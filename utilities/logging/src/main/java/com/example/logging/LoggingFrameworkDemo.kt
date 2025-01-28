package com.example.logging

import com.example.logging.logappender.ConsoleAppender
import com.example.logging.logappender.FileAppender


fun main() {

    // Logging with default configuration
    Logger.info("This is an information message")
    Logger.warning("This is a warning message")
    Logger.error("This is an error message")

    // Changing log level and appender
//        val config = LoggerConfig(LogLevel.DEBUG, FileAppender("app.log"))
    val config = LoggerConfig(LogLevel.DEBUG)
    Logger.setConfig(config)

    Logger.log(LogLevel.ERROR, "logging directly", ConsoleAppender())

    Logger.debug("This is a debug message")
    Logger.info("This is an information message")
}