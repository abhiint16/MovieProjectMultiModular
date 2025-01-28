package com.example.logging

data class LogMessage(val level: LogLevel, val message: String) {
    val timestamp: Long = System.currentTimeMillis()

    override fun toString(): String {
        return "[$level] $timestamp - $message"
    }
}