package com.app.data.movies

import com.app.data.base.repository.BaseRepositoryImpl
import com.app.data.movies.local.MovieLocalSource
import com.app.data.movies.mapper.MovieDataMapper
import com.app.data.movies.mapper.MovieDomainMapper
import com.app.data.movies.model.MovieEntity
import com.app.data.movies.model.MoviePojo
import com.app.data.movies.remote.MovieRemoteSource
import com.app.domain.movies.MovieRepository
import com.app.domain.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localSource: MovieLocalSource,
    private val domainMapper: MovieDomainMapper,
    private val dataMapper: MovieDataMapper,
    private val remoteSource: MovieRemoteSource
) : MovieRepository, BaseRepositoryImpl<Movie, MovieEntity, MoviePojo>(
    localSource,
    dataMapper,
    domainMapper
) {

    override fun getAll(): Flow<List<Movie>> {
        return operationGetAll.asFlow().map { entities ->
            entities.map {
                domainMapper.mapRemote(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchAll(): List<MoviePojo> {
        return remoteSource.getAllMovies()
    }

    override suspend fun getMovieById(id: String): Movie {
        val moviePojo = remoteSource.getMovieById(id)
        return domainMapper.mapFromPojo(moviePojo)
    }

}
