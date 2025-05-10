package com.app.domain.series.uc

import com.app.domain.series.SeriesRepository
import com.app.domain.series.model.Series
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetSeriesByIdUC @Inject constructor(
    private val seriesRepository: SeriesRepository
) {

    operator fun invoke(id: String): Flow<Series?> {
        return seriesRepository.getById(id)
    }

    suspend fun refresh() {
        seriesRepository.refresh()
    }
}