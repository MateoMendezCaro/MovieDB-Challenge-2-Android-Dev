package com.app.moviedb.movies.compose

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.app.moviedb.base.navigation.Destination
import com.app.ui_common.components.theme.AppTheme
import com.app.moviedb.movies.model.MovieUI
import com.app.moviedb.movies.model.MovieUIState
import com.app.moviedb.movies.viewmodel.MovieViewModel
import com.app.ui_common.components.MediaCard
import com.app.ui_common.components.util.GeneralEmptyScreen
import com.app.ui_common.components.util.GeneralLoadingScreen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MoviesScreen(
    innerPadding: PaddingValues,
    viewModel: MovieViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val stateUI by viewModel.state.collectAsStateWithLifecycle()
    when (val state = stateUI) {
        is MovieUIState.Loading -> GeneralLoadingScreen()
        is MovieUIState.Empty -> GeneralEmptyScreen()
        is MovieUIState.Show -> {
            Movies(innerPadding, state.movies, navController)
        }
    }
}

@Composable
fun Movies(
    innerPadding: PaddingValues,
    movies: ImmutableList<MovieUI>,
    navController: NavHostController
) {
    Box(
        Modifier
            .padding(innerPadding)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies.size) { index ->
                with(movies[index]) {
                    MediaCard(imageUrl = imageUrl, title = title, isForAdult = isForAdult, onMediaCardClick = { clickedId ->
                        Log.d("MediaCard", "Card clicked, id: $clickedId")
                        navController.navigate(Destination.DetailScreen(clickedId, "movie"))
                    })
                }
            }
        }
    }
}
