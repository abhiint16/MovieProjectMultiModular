package com.example.samplepracticewithoutseeing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.common.MovieNavigationConstant
import com.example.samplepracticewithoutseeing.navigationprovider.NavigationProvider

@Composable
fun AppNavGraph(navController: NavHostController, navigationProvider: NavigationProvider) {

    NavHost(navController = navController, startDestination = MovieNavigationConstant.movieRoute) {
        navigationProvider.movieNavigation.registerGraph(navController, this)

        navigationProvider.movieDetailNavigation.registerGraph(navController, this)
    }
}