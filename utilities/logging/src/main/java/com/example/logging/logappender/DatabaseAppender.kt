package com.example.logging.logappender

import com.example.logging.LogMessage
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseAppender(
    private val jdbcUrl: String,
    private val username: String,
    private val password: String
) : LogAppender {

    override fun append(logMessage: LogMessage) {
        try {
            DriverManager.getConnection(jdbcUrl, username, password).use { connection ->
                connection.prepareStatement("INSERT INTO logs (level, message, timestamp) VALUES (?, ?, ?)")
                    .use { statement ->
                        statement.setString(1, logMessage.level.toString())
                        statement.setString(2, logMessage.message)
                        statement.setLong(3, logMessage.timestamp)
                        statement.executeUpdate()
                    }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}