package com.app.moviedb.people.model

import kotlinx.collections.immutable.ImmutableList

sealed class PeopleUIState {
    data object Loading : PeopleUIState()
    data class Show(val people: ImmutableList<PeopleUI>) : PeopleUIState()
    data class Empty(val people: ImmutableList<PeopleUI>) : PeopleUIState()
}