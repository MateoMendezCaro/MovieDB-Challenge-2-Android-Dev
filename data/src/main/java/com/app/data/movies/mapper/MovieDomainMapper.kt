package com.app.data.movies.mapper

import com.app.data.base.mapper.MapperDomain
import com.app.data.movies.model.MovieEntity
import com.app.data.movies.model.MoviePojo
import com.app.domain.movies.model.Movie
import javax.inject.Inject

class MovieDomainMapper @Inject constructor() : MapperDomain<Movie, MovieEntity> {

    override fun mapRemote(entity: MovieEntity): Movie {
        return Movie(
            entity.id,
            entity.title,
            entity.overview,
            entity.posterPath,
            entity.voteAverage,
            entity.isForAdult,
            entity.releaseDate,
        )
    }

    fun mapFromPojo(pojo: MoviePojo): Movie {
        return Movie(
            pojo.id,
            pojo.title,
            pojo.overview,
            pojo.posterPath,
            pojo.voteAverage,
            pojo.isForAdult,
            pojo.releaseDate,
        )
    }
}