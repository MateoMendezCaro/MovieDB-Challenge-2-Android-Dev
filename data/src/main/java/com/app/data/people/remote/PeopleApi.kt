package com.app.data.people.remote

import com.app.data.people.model.PeopleResponse
import retrofit2.http.GET

interface PeopleApi {
    @GET("trending/person")
    suspend fun getTrendingPeople(): PeopleResponse
}