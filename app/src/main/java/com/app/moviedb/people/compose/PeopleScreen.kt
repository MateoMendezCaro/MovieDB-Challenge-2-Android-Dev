package com.app.moviedb.people.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.moviedb.people.model.PeopleUIState
import com.app.moviedb.people.viewmodel.PeopleViewModel
import com.app.ui_common.components.PeopleMediaCard
import com.app.ui_common.components.util.GeneralEmptyScreen
import com.app.ui_common.components.util.GeneralLoadingScreen
import kotlinx.collections.immutable.ImmutableList
import com.app.moviedb.people.model.PeopleUI

@Composable
fun PeopleScreen(
    innerPadding: PaddingValues,
    viewModel: PeopleViewModel = hiltViewModel()
) {
    val stateUI by viewModel.state.collectAsStateWithLifecycle()
    when (val state = stateUI) {
        is PeopleUIState.Loading -> GeneralLoadingScreen()
        is PeopleUIState.Empty -> GeneralEmptyScreen()
        is PeopleUIState.Show -> {
            People(innerPadding = innerPadding, people = state.people)
        }
    }
}

@Composable
fun People(
    innerPadding: PaddingValues,
    people: ImmutableList<PeopleUI>
) {
    Box(modifier = Modifier.padding(innerPadding)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(people.size) { index ->
                with(people[index]) {
                    PeopleMediaCard(id = id, profileImageUrl = profileImageUrl, name = name)
                }
            }
        }
    }
}