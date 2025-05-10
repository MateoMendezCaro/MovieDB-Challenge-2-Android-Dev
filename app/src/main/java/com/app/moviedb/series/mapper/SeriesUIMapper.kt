package com.app.moviedb.series.mapper

import com.app.domain.series.model.Series
import com.app.moviedb.series.model.SeriesUI
import com.app.moviedb.series.model.SeriesUIState
import kotlinx.collections.immutable.toPersistentList
import javax.inject.Inject

class SeriesUIMapper @Inject constructor() {

    fun buildUI(series: List<Series>): SeriesUIState {
        if (series.isEmpty()) {
            return SeriesUIState.Empty
        }
        return SeriesUIState.Show(
            series.filter { it.voteAverage > 5.toDouble() }.map {
                SeriesUI(
                    id = it.id.orEmpty(),
                    title = it.title,
                    imageUrl = it.posterPath,
                    voteAverage = it.voteAverage,
                    isForAdult = it.isForAdult
                )
            }.toPersistentList()
        )
    }
}