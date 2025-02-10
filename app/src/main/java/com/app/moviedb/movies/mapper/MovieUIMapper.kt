package com.app.moviedb.movies.mapper

import com.app.domain.movies.model.Movie
import com.app.moviedb.movies.model.MovieUI
import com.app.moviedb.movies.model.MovieUIState
import kotlinx.collections.immutable.toPersistentList
import javax.inject.Inject

class MovieUIMapper @Inject constructor() {


    fun buildUI(movies: List<Movie>): MovieUIState {
        return MovieUIState.Show(
            movies.map {
                MovieUI(id = it.id.orEmpty(), title = it.title, imageUrl = it.posterPath, isForAdult = it.isForAdult)
            }.toPersistentList()
        )
    }
}