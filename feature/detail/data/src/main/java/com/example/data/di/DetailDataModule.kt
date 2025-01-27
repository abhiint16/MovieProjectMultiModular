package com.example.data.di

import com.example.data.repository.MovieDetailRepoImpl
import com.example.domain.repository.MovieDetailRepo
import com.example.network.services.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DetailDataModule {

    @Provides
    fun providesMovieDetailRepository(movieService: MovieService): MovieDetailRepo {
        return MovieDetailRepoImpl(movieService)
    }
}