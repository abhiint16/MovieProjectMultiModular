package com.example.ui.navigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.common.MovieNavigationConstant
import com.example.navigation.NavigationApi
import com.example.ui.screen.MovieScreen
import com.example.ui.viewmodel.MovieViewModel

interface MovieNavigation : NavigationApi

class MovieNavigationImpl : MovieNavigation {
    override fun registerGraph(navController: NavController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = MovieNavigationConstant.movieInternalRoute,
            route = MovieNavigationConstant.movieRoute
        ) {
            composable(route = MovieNavigationConstant.movieInternalRoute) {
                val viewModel = hiltViewModel<MovieViewModel>()
                CompositionLocalProvider(LocalLifecycleOwner provides it) {
                    MovieScreen(viewModel, navController)
                }
            }
        }
    }
}

val LocalLifecycleOwner = staticCompositionLocalOf<LifecycleOwner> {
    error("No NavigationAction specified")
}