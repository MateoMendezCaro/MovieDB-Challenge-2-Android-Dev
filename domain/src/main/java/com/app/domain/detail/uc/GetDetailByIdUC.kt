package com.app.domain.detail.uc

import com.app.domain.detail.DetailRepository
import com.app.domain.detail.model.Detail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailByIdUC @Inject constructor(
    private val detailRepository: DetailRepository
) {
    fun invoke(id: String, isMovie: Boolean): Flow<Detail> {
        return detailRepository.getDetailById(id, isMovie)
    }
}