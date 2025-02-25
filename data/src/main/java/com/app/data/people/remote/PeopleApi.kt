package com.app.data.people.remote

import com.app.data.people.model.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleApi {
    @GET("trending/person/{time_window}")
    suspend fun getTrendingPeople(
        @Path("time_window") timeWindow: String = "day"
    ): PeopleResponse
}