package com.example.common.abstraction

interface FileDownloader {

    suspend fun downloadResource(downloadUrl: String, destPath: String)
}