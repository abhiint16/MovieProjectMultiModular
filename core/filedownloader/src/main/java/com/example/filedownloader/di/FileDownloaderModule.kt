package com.example.filedownloader.di

import com.example.common.abstraction.FileDownloader
import com.example.filedownloader.FileDownloaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FileDownloaderModule {

    @Provides
    fun providesFileDownloader(): FileDownloader {
        return FileDownloaderImpl()
    }
}