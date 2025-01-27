package com.example.data.di

import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import com.example.network.services.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MovieModule {

    @Provides
    fun providesMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }
}