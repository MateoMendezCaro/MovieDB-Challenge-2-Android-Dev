package com.app.moviedb.series.model

data class SeriesUI(
    val id: String,
    val title: String,
    val imageUrl: String,
    val voteAverage: Double,
    val isForAdult: Boolean,
)