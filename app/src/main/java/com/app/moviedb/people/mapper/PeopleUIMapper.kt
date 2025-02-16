package com.app.moviedb.people.mapper

import com.app.domain.people.model.People
import com.app.moviedb.people.model.PeopleUI
import com.app.moviedb.people.model.PeopleUIState
import kotlinx.collections.immutable.toPersistentList
import javax.inject.Inject

class PeopleUIMapper @Inject constructor() {

    fun buildUI(people: List<People>): PeopleUIState {
        return PeopleUIState.Show(
            people.map {
                PeopleUI(id = it.id.orEmpty(), name = it.name, profileImageUrl = it.profileImageUrl)
            }.toPersistentList()
        )
    }
}