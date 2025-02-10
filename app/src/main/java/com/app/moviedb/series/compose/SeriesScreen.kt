package com.app.moviedb.series.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.app.moviedb.base.main.compose.ViewEventHost
import com.app.moviedb.base.navigation.Destination
import com.app.moviedb.series.model.SeriesUI
import com.app.moviedb.series.model.SeriesUIState
import com.app.moviedb.series.viewmodel.SeriesViewModel
import com.app.ui_common.components.MediaCard
import com.app.ui_common.components.util.GeneralEmptyScreen
import com.app.ui_common.components.util.GeneralLoadingScreen
import kotlinx.collections.immutable.ImmutableList


@Composable
fun SeriesScreen(
    innerPadding: PaddingValues,
    viewModel: SeriesViewModel = hiltViewModel(),
    navController: NavHostController
) {
    ViewEventHost(viewModel)

    LaunchedEffect(Unit) {
        viewModel.loadSeries()
    }

    val stateUI by viewModel.state.collectAsStateWithLifecycle()

    when (val state = stateUI) {
        is SeriesUIState.Loading -> GeneralLoadingScreen()
        is SeriesUIState.Empty -> GeneralEmptyScreen()
        is SeriesUIState.Show -> {
            Series(innerPadding, state.series, navController)
        }
    }
}

@Composable
fun Series(
    innerPadding: PaddingValues,
    series: ImmutableList<SeriesUI>,
    navController: NavHostController
) {
    Box(
        Modifier.padding(innerPadding)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(series.size) { index ->
                with(series[index]) {
                    MediaCard(
                        id = id,
                        imageUrl = imageUrl,
                        title = title,
                        isForAdult = isForAdult,
                        onMediaCardClick = { clickedId ->
                            navController.navigate(Destination.DetailScreen(clickedId, "series"))
                        }
                    )
                }
            }
        }
    }
}