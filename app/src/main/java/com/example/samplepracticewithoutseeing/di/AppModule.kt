package com.example.samplepracticewithoutseeing.di

import android.content.Context
import com.example.samplepracticewithoutseeing.navigationprovider.NavigationProvider
import com.example.ui.navigation.MovieDetailNavigation
import com.example.ui.navigation.MovieNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun providesNavigationProvider(
        movieNavigation: MovieNavigation,
        movieDetailNavigation: MovieDetailNavigation
    ): NavigationProvider {
        return NavigationProvider(movieNavigation, movieDetailNavigation)
    }

    @Provides
    @Named("cacheDirPath")
    fun providesCacheDirPath(@ApplicationContext context: Context): String {
        return context.cacheDir.path
    }
}