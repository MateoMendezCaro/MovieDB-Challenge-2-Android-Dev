package com.app.moviedb.details.mapper

import com.app.domain.movies.model.Movie
import com.app.domain.series.model.Series
import com.app.moviedb.details.model.DetailUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailUIMapper @Inject constructor()  {
    fun mapFromMovie(movieFlow: Flow<Movie?>): Flow<DetailUI?> {
        return movieFlow.map { movie ->
            movie?.let {
                DetailUI(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage,
                    isForAdult = it.isForAdult,
                    releaseDate = it.releaseDate
                )
            }
        }
    }

    fun mapFromSeries(seriesFlow: Flow<Series?>): Flow<DetailUI?> {
        return seriesFlow.map { series ->
            series?.let {
                DetailUI(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage,
                    isForAdult = it.isForAdult,
                    releaseDate = it.releaseDate
                )
            }
        }
    }
}