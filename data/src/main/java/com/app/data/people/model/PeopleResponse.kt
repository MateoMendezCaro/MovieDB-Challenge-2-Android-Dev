package com.app.data.people.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @Expose
    @SerializedName("page")
    val page: Int,
    @Expose
    @SerializedName("results")
    val results: List<PeoplePojo>,
    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,
    @Expose
    @SerializedName("total_results")
    val totalResults: Int
)