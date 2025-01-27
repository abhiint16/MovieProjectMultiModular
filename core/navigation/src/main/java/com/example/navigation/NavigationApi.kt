package com.example.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface NavigationApi {
    fun registerGraph(navController: NavController, navGraphBuilder: NavGraphBuilder)
}