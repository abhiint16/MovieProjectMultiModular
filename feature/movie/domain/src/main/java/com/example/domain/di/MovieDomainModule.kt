package com.example.domain.di

import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MovieDomainModule {

    @Provides
    fun providesMovieUseCase(movieRepository: MovieRepository): GetMovieUseCase {
        return GetMovieUseCase(movieRepository)
    }
}