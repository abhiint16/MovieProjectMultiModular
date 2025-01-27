package com.example.samplepracticewithoutseeing.navigationprovider

import com.example.ui.navigation.MovieDetailNavigation
import com.example.ui.navigation.MovieNavigation

data class NavigationProvider(
    val movieNavigation: MovieNavigation,
    val movieDetailNavigation: MovieDetailNavigation
)
