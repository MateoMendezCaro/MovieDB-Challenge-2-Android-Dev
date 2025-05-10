package com.app.moviedb.details.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.app.moviedb.details.model.DetailUI
import com.app.moviedb.details.viewmodel.DetailViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.moviedb.details.model.DetailUIState
import com.app.ui_common.components.theme.AppTheme

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
        is DetailUIState.Loading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        is DetailUIState.Success -> {
            val detail = (uiState as DetailUIState.Success).detail
            DetailContent(detail)
        }
        is DetailUIState.Error -> Text("Error loading details.", color = MaterialTheme.colorScheme.error)
    }
}
@Composable
fun DetailContent(detail: DetailUI) {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = rememberImagePainter(detail.posterPath),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(colors.surface.copy(alpha = 0.7f))
                    .padding(8.dp)
            ) {
                Text(
                    text = detail.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = colors.onSurface,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = detail.releaseDate,
            style = MaterialTheme.typography.headlineSmall,
            color = colors.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Divider(
            color = colors.onBackground.copy(alpha = 0.5f),
            thickness = 1.dp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(0.75f)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(colors.surfaceVariant, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rate",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = detail.voteAverage.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(colors.surfaceVariant, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val (icon, text) = if (detail.isForAdult) "🔞" to "Adults" else "🟢" to "All public"
                    Text(text = icon, fontSize = 32.sp)
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }

        Divider(
            color = colors.onBackground.copy(alpha = 0.5f),
            thickness = 1.dp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(0.75f)
                .align(Alignment.CenterHorizontally)
        )

        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberImagePainter(detail.posterPath),
                contentDescription = "Poster",
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = detail.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.onBackground,
                modifier = Modifier.weight(1f)
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .padding(16.dp)
                .height(60.dp)
                .width(180.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Add to Favorites")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    AppTheme {
        DetailContent(
            DetailUI(
                id = "1",
                title = "The Shawshank Redemption",
                overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                releaseDate = "1994-09-23",
                voteAverage = 9.3,
                isForAdult = false,
                posterPath = "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
            )
        )
    }
}
