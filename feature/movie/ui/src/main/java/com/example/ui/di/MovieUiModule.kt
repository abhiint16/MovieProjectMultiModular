package com.example.ui.di

import com.example.ui.navigation.MovieNavigation
import com.example.ui.navigation.MovieNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MovieUiModule {

    @Provides
    fun providesMovieNavigation(): MovieNavigation {
        return MovieNavigationImpl()
    }
}