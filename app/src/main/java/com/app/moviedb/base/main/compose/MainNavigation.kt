package com.app.moviedb.base.main.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.app.moviedb.base.navigation.Destination
import com.app.moviedb.base.navigation.NavHost
import com.app.moviedb.base.navigation.composable
import com.app.moviedb.details.compose.DetailScreen
import com.app.moviedb.home.compose.HomeScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.HomeScreen
    ) {
        composable(destination = Destination.HomeScreen) {
            HomeScreen(navController = navController)
        }
        composable(destination = Destination.DetailScreen) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val type = backStackEntry.arguments?.getString("type") ?: ""
            DetailScreen(
                id = id,
                isMovie = (type == "movie"),
                navController = navController
            )
        }
    }
}