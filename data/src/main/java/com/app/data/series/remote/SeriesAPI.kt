package com.app.data.series.remote

import com.app.data.series.model.SeriesPojo
import com.app.data.series.model.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesAPI {


    @GET("discover/tv")
    suspend fun getAllSeries(): SeriesResponse

    @GET("series/{id}")
    suspend fun getSeriesById(@Path("id") id: String): SeriesPojo
}