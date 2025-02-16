package com.app.moviedb.home.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.app.moviedb.movies.compose.MoviesScreen
import com.app.moviedb.people.compose.PeopleScreen
import com.app.moviedb.profile.compose.ProfileScreen
import com.app.moviedb.series.compose.SeriesScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Movies", "Series","People","Profile")
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            when (index) {
                                0 -> Icon(Icons.Filled.Build, contentDescription = item)
                                1 -> Icon(Icons.Filled.Person, contentDescription = item)
                                2 -> Icon(Icons.Filled.Star, contentDescription = item)
                                3 -> Icon(Icons.Filled.Call, contentDescription = item)
                                else -> {}
                            }
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index })
                }
            }
        }
    ) { innerPadding ->
        Navigation(selectedItem, innerPadding, navController)
    }
}

@Composable
fun Navigation(selectedItem: Int, innerPadding: androidx.compose.foundation.layout.PaddingValues, navController: NavHostController) {
    when (selectedItem) {
        0 -> MoviesScreen(innerPadding,navController = navController)
        1 -> SeriesScreen(innerPadding,navController = navController)
        2 -> PeopleScreen(innerPadding)
        3 -> ProfileScreen(innerPadding)

    }
}
