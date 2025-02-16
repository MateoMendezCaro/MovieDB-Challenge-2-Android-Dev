package com.app.domain.people.model
data class People(
    val id: String? = null,
    val name: String,
    val originalName: String,
    val mediaType: String,
    val adult: Boolean,
    val popularity: Double,
    val gender: Int,
    val knownForDepartment: String,
    val profileImageUrl: String
)
