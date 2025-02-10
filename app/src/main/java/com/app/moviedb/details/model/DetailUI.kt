package com.app.moviedb.details.model

data class DetailUI(
    val id: String?,
    val title: String,
    val overview: String,
    val posterPath: String,
    val isForAdult: Boolean,
    val releaseDate: String,
    val voteAverage: Double
)