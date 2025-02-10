package com.app.domain.detail.model

data class Detail (
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double,
    val isForAdult: Boolean,
    val releaseDate: String
)