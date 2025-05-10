package com.app.data.movies.remote

import com.app.data.movies.model.MoviePojo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteSource @Inject constructor(
    private val api: MovieAPI
) {

    suspend fun getAllMovies(): List<MoviePojo> {
        return api.getAllMovies().results
    }
    suspend fun getMovieById(id: String): MoviePojo {
        return api.getMovieById(id)
    }
}