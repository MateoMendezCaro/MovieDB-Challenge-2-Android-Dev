package com.app.data.people.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class PeoplePojo(
    @Expose
    @SerializedName("id")
    var id: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("original_name")
    val original_name: String,
    @Expose
    @SerializedName("media_type")
    val media_type: String,
    @Expose
    @SerializedName("adult")
    val adult: Boolean,
    @Expose
    @SerializedName("popularity")
    val popularity: Double,
    @Expose
    @SerializedName("gender")
    val gender: Int,
    @Expose
    @SerializedName("known_for_department")
    val known_for_department: String,
    @Expose
    @SerializedName("profile_path")
    val profile_path: String?
)