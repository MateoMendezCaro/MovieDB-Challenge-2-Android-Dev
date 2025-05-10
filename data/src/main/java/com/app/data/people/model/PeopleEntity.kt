package com.app.data.people.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.data.base.local.IdentifyEntity

@Entity
data class PeopleEntity(
    @PrimaryKey
    override val id: String,
    val name: String,
    val originalName: String,
    val mediaType: String,
    val adult: Boolean,
    val popularity: Double,
    val gender: Int,
    val knownForDepartment: String,
    val profilePath: String
) : IdentifyEntity
