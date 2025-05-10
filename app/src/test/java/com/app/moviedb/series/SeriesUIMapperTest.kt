package com.app.moviedb.series

import com.app.domain.series.model.Series
import com.app.moviedb.series.mapper.SeriesUIMapper
import com.app.moviedb.series.model.SeriesUI
import com.app.moviedb.series.model.SeriesUIState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SeriesUIMapperTest {
    private val mapper = SeriesUIMapper()

    @Test
    fun `buildUI returns Empty when series list is empty`() = runTest {
        val seriesList = emptyList<Series>()
        val result = mapper.buildUI(seriesList)
        assertEquals(SeriesUIState.Empty, result)
    }

    @Test
    fun `buildUI filters out series with voteAverage less than or equal to 5`() = runTest {
        val seriesList = listOf(
            Series(id = "1", title = "Series 1", overview = "", posterPath = "", voteAverage = 4.0, isForAdult = false, releaseDate = "", popularity = 10.0),
            Series(id = "2", title = "Series 2", overview = "", posterPath = "", voteAverage = 6.0, isForAdult = false, releaseDate = "", popularity = 20.0)
        )
        val result = mapper.buildUI(seriesList)
        val expected = SeriesUIState.Show(
            persistentListOf(
                SeriesUI(
                    id = "2",
                    title = "Series 2",
                    imageUrl = "",
                    voteAverage = 6.0,
                    isForAdult = it.isForAdult
                )
            )
        )
        assertEquals(expected, result)
    }

    @Test
    fun `buildUI maps series list to SeriesUIState_Show correctly`() = runTest {
        val seriesList = listOf(
            Series(id = "1", title = "Series 1", overview = "", posterPath = "/path1", voteAverage = 6.0, isForAdult = false, releaseDate = "", popularity = 10.0),
            Series(id = "2", title = "Series 2", overview = "", posterPath = "/path2", voteAverage = 7.0, isForAdult = false, releaseDate = "", popularity = 20.0)
        )
        val result = mapper.buildUI(seriesList)
        val expected = SeriesUIState.Show(
            persistentListOf(
                SeriesUI(
                    id = "1",
                    title = "Series 1",
                    imageUrl = "/path1",
                    voteAverage = 6.0,
                    isForAdult = it.isForAdult
                ), SeriesUI(
                    id = "2",
                    title = "Series 2",
                    imageUrl = "/path2",
                    voteAverage = 7.0,
                    isForAdult = it.isForAdult
                )
            )
        )
        assertEquals(expected, result)
    }

    @Test
    fun `buildUI handles null id`() {
        val seriesList = listOf(
            Series(title = "Serie sin ID", overview = "", posterPath = "/poster", voteAverage = 6.5, isForAdult = false, releaseDate = "", popularity = 0.0)
        )

        val expectedUIState = SeriesUIState.Show(
            listOf(
                SeriesUI(
                    id = "",
                    title = "Serie sin ID",
                    imageUrl = "/poster",
                    voteAverage = 6.5,
                    isForAdult = it.isForAdult
                )
            ).toPersistentList()
        )

        val result = mapper.buildUI(seriesList)
        assertEquals(expectedUIState, result)
    }
}