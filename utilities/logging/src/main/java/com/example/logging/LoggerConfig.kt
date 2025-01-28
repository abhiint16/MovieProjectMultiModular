package com.example.logging

import com.example.logging.logappender.ConsoleAppender
import com.example.logging.logappender.LogAppender

data class LoggerConfig(var logLevel: LogLevel, var logAppender: LogAppender = ConsoleAppender())