package com.app.domain.series

import com.app.domain.base.BaseRepository
import com.app.domain.movies.model.Movie
import com.app.domain.series.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesRepository : BaseRepository<Series> {

    fun getAll(): Flow<List<Series>>
    suspend fun getSeriesById(id: String): Series
}