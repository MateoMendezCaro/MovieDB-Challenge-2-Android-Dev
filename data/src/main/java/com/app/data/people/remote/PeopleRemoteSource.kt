package com.app.data.people.remote

import com.app.data.people.model.PeoplePojo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRemoteSource @Inject constructor(
    private val api: PeopleApi
) {

    suspend fun getAllPeople(): List<PeoplePojo> {
        return api.getTrendingPeople().results
    }
}