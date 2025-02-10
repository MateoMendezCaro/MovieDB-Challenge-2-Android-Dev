package com.app.moviedb.series.compose

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.moviedb.base.main.compose.ViewEventHost
import com.app.ui_common.components.theme.AppTheme
import com.app.moviedb.series.model.SeriesUI
import com.app.moviedb.series.model.SeriesUIState
import com.app.moviedb.series.viewmodel.SeriesViewModel
import com.app.ui_common.components.MediaCard
import com.app.ui_common.components.util.GeneralEmptyScreen
import com.app.ui_common.components.util.GeneralLoadingScreen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SeriesScreen(
    innerPadding: PaddingValues,
    viewModel: SeriesViewModel = hiltViewModel(),
) {
    ViewEventHost(viewModel)
    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }
    val stateUI by viewModel.state.collectAsStateWithLifecycle()
    when (val state = stateUI) {
        is SeriesUIState.Loading -> GeneralLoadingScreen()
        is SeriesUIState.Empty -> GeneralEmptyScreen()
        is SeriesUIState.Show -> {
            Series(innerPadding, state.series)
        }
    }
}

@Composable
fun Series(
    innerPadding: PaddingValues,
    series: ImmutableList<SeriesUI>,
) {
    val context = LocalContext.current

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
            items(series.size) { index ->
                with(series[index]) {
                    MediaCard(
                        imageUrl = imageUrl,
                        title = title,
                        popularity = voteAverage.toString(),
                        onMediaCardClick = {
                            Toast.makeText(context, "Item with Id $it clicked", Toast.LENGTH_SHORT).show()
                        },
                        isForAdult = isForAdult
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSeries() {
    AppTheme {
        Series(
            PaddingValues(),
            persistentListOf(
                SeriesUI(
                    "1",
                    "The Shawshank Redemption",
                    "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                    2.0,
                    isForAdult = true
                ),
                SeriesUI(
                    "3",
                    "The Dark Knight",
                    "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                    3.9,
                    isForAdult = false
                )
            )
        )
    }
}