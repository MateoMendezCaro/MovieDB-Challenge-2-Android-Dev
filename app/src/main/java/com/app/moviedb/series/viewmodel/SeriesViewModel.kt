package com.app.moviedb.series.viewmodel

import com.app.domain.series.uc.GetAllSeriesUC
import com.app.moviedb.base.viewmodel.BaseViewModel
import com.app.moviedb.series.mapper.SeriesUIMapper
import com.app.moviedb.series.model.SeriesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val movieUIMapper: SeriesUIMapper,
    private val getAllSeriesUC: GetAllSeriesUC
) : BaseViewModel<SeriesUIState>(SeriesUIState.Loading) {


    fun loadSeries() = launchWithErrorHandlingDefault {
        getAllSeriesUC.invoke().collect {
            _state.value = movieUIMapper.buildUI(it)
        }
    }

    fun refresh() = launchWithErrorHandlingDefault {
        getAllSeriesUC.refresh()
    }
}