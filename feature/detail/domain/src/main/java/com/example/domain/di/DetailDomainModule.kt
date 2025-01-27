package com.example.domain.di

import com.example.common.abstraction.FileDownloader
import com.example.domain.repository.MovieDetailRepo
import com.example.domain.usecase.DownloadImageUseCase
import com.example.domain.usecase.GetMovieDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
class DetailDomainModule {

    @Provides
    fun providesDetailUseCase(movieDetailRepo: MovieDetailRepo): GetMovieDetailUseCase {
        return GetMovieDetailUseCase((movieDetailRepo))
    }

    @Provides
    fun providesDownloaderUseCase(
        fileDownloader: FileDownloader,
        @Named("cacheDirPath") cacheDirPath: String
    ): DownloadImageUseCase {
        return DownloadImageUseCase(fileDownloader, cacheDirPath)
    }
}