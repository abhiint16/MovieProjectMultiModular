package com.example.ui.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.common.MovieDetailNavigationConstant
import com.example.common.MovieNavigationConstant
import com.example.navigation.NavigationApi
import com.example.ui.screen.DetailViewModel
import com.example.ui.screen.MovieDetailScreen
import com.example.ui.screen.MovieImageFullScreen

interface MovieDetailNavigation : NavigationApi {
}

class MovieDetailNavigationImpl : MovieDetailNavigation {

    override fun registerGraph(navController: NavController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = MovieDetailNavigationConstant.movieDetailRoute,
            route = MovieDetailNavigationConstant.movieDetailInternalRoute
        ) {
            composable(route = MovieDetailNavigationConstant.movieDetailRoute) { navBackStackEntry ->
                val parentEntry = remember(navBackStackEntry) {
                    navController.getBackStackEntry(MovieDetailNavigationConstant.movieDetailRoute)
                }
                val id = navBackStackEntry.arguments?.getString("id")
                val viewModel = hiltViewModel<DetailViewModel>(parentEntry)
                MovieDetailScreen(id.toString(), viewModel, navController)
            }
            composable(route = MovieDetailNavigationConstant.movieDetailFullScreenInternalRoute) { navBackStackEntry ->
                val parentEntry = remember(navBackStackEntry) {
                    navController.getBackStackEntry(MovieDetailNavigationConstant.movieDetailRoute)
                }
                val viewModel = hiltViewModel<DetailViewModel>(parentEntry)
                MovieImageFullScreen(viewModel)
            }
        }
    }

}