package com.app.domain.movies.uc

import com.app.domain.movies.MovieRepository
import com.app.domain.movies.model.Movie
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetMovieByIdUC @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(id: String): Flow<Movie?> {
        return movieRepository.getById(id)
    }

    suspend fun refresh() {
        movieRepository.refresh()
    }

}