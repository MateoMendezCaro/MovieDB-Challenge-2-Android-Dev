package com.app.moviedb.details.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.moviedb.details.model.DetailUI
import com.app.moviedb.details.viewmodel.DetailViewModel
import com.app.moviedb.details.model.DetailUIState
import com.app.ui_common.components.util.GeneralLoadingScreen
import com.app.ui_common.components.util.GeneralEmptyScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    id: String,
    isMovie: Boolean,
    navController: NavHostController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadDetail(id, isMovie)
    }

    when (uiState) {
        is DetailUIState.Loading -> CircularProgressIndicator()
        is DetailUIState.Success -> {
            val detail = (uiState as DetailUIState.Success).detail
            DetailContent(detail)
        }
        is DetailUIState.Error -> Text("Error al cargar los detalles.")
    }
}

@Composable
fun DetailContent(detail: DetailUI) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(detail.posterPath),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = detail.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = detail.overview, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Fecha de lanzamiento: ${detail.releaseDate}")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(text = "⭐ ${detail.voteAverage}")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = if (detail.isForAdult) "🔞 Adultos" else "🟢 Apto para todo público")
        }
    }
}