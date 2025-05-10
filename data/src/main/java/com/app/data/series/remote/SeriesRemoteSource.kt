package com.app.data.series.remote

import com.app.data.series.model.SeriesPojo
import javax.inject.Inject

class SeriesRemoteSource  @Inject constructor(
    private val api: SeriesAPI
) {

    suspend fun getAllSeries(): List<SeriesPojo> {
        return api.getAllSeries().results
    }
    suspend fun getSeriesById(id: String): SeriesPojo {
        return api.getSeriesById(id)
    }
}
