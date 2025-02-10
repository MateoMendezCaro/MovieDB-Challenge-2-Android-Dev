package com.app.data.series

import com.app.data.base.repository.BaseRepositoryImpl
import com.app.data.series.local.SeriesLocalSource
import com.app.data.series.mapper.SeriesDataMapper
import com.app.data.series.mapper.SeriesDomainMapper
import com.app.data.series.model.SeriesEntity
import com.app.data.series.model.SeriesPojo
import com.app.data.series.remote.SeriesRemoteSource
import com.app.domain.series.SeriesRepository
import com.app.domain.series.model.Series
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val localSource: SeriesLocalSource,
    private val domainMapper: SeriesDomainMapper,
    private val dataMapper: SeriesDataMapper,
    private val remoteSource: SeriesRemoteSource
) : SeriesRepository, BaseRepositoryImpl<Series, SeriesEntity, SeriesPojo>(
    localSource,
    dataMapper,
    domainMapper
) {

    override fun getAll(): Flow<List<Series>> {
        return operationGetAll.asFlow().map { entities ->
            entities.map {
                domainMapper.mapRemote(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchAll(): List<SeriesPojo> {
        return remoteSource.getAllSeries()
    }
    override suspend fun getSeriesById(id: String): Series {
        val seriesPojo = remoteSource.getSeriesById(id)
        return domainMapper.mapFromPojo(seriesPojo)
    }
}

