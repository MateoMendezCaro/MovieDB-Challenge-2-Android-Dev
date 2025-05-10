package com.app.moviedb.people.viewmodel

import com.app.domain.people.uc.GetTrendingPeopleUC
import com.app.moviedb.base.viewmodel.BaseViewModel
import com.app.moviedb.people.mapper.PeopleUIMapper
import com.app.moviedb.people.model.PeopleUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val peopleUIMapper: PeopleUIMapper,
    private val getTrendingPeopleUC: GetTrendingPeopleUC
) : BaseViewModel<PeopleUIState>(PeopleUIState.Loading) {

    init {
        loadPeople()
    }

    private fun loadPeople() = launchWithErrorHandlingDefault {
        getTrendingPeopleUC.invoke().collect {
            _state.value = peopleUIMapper.buildUI(it)
        }
    }

    fun refresh() = launchWithErrorHandlingDefault {
        getTrendingPeopleUC.refresh()
    }
}