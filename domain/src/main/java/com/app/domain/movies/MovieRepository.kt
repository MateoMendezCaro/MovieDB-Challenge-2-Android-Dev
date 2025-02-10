package com.app.domain.movies

import com.app.domain.base.BaseRepository
import com.app.domain.movies.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository : BaseRepository<Movie> {

    fun getAll(): Flow<List<Movie>>
    suspend fun getMovieById(id: String): Movie
}