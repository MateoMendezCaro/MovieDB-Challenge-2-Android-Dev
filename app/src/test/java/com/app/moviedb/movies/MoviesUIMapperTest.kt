package com.app.moviedb.movies

import com.app.domain.movies.model.Movie
import com.app.moviedb.movies.mapper.MovieUIMapper
import com.app.moviedb.movies.model.MovieUI
import com.app.moviedb.movies.model.MovieUIState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesUIMapperTest {

    private val mapper = MovieUIMapper()

    @Test
    fun `buildUI maps movies correctly`() {
        val movies = listOf(
            Movie(id = "1", title = "Película 1", overview = "", posterPath = "/poster1", voteAverage = 0.0, isForAdult = false, releaseDate = ""),
            Movie(id = "2", title = "Película 2", overview = "", posterPath = "/poster2", voteAverage = 0.0, isForAdult = false, releaseDate = "")
        )

        val expectedUIState = MovieUIState.Show(
            listOf(
                MovieUI(
                    id = "1",
                    title = "Película 1",
                    imageUrl = "/poster1",
                    isForAdult = false
                ),
                MovieUI(
                    id = "2",
                    title = "Película 2",
                    imageUrl = "/poster2",
                    isForAdult = false
                )
            ).toPersistentList()
        )

        val result = mapper.buildUI(movies)
        assertEquals(expectedUIState, result)
    }

    @Test
    fun `buildUI handles empty movies list`() {
        val movies = emptyList<Movie>()

        val expectedUIState = MovieUIState.Show(persistentListOf())

        val result = mapper.buildUI(movies)
        assertEquals(expectedUIState, result)
    }

    @Test
    fun `buildUI handles null id gracefully`() {
        val movies = listOf(
            Movie(title = "Película sin ID", overview = "", posterPath = "/poster", voteAverage = 0.0, isForAdult = false, releaseDate = "")
        )

        val expectedUIState = MovieUIState.Show(
            listOf(
                MovieUI(
                    id = "",
                    title = "Película sin ID",
                    imageUrl = "/poster",
                    isForAdult = false
                )
            ).toPersistentList()
        )

        val result = mapper.buildUI(movies)
        assertEquals(expectedUIState, result)
    }

}