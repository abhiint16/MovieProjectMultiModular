package com.example.ui.di

import com.example.ui.navigation.MovieDetailNavigation
import com.example.ui.navigation.MovieDetailNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DetailUiModule {

    @Provides
    fun providesMovieDetailNavigation(): MovieDetailNavigation {
        return MovieDetailNavigationImpl()
    }
}