package com.app.domain.detail

import com.app.domain.detail.model.Detail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getDetailById(id: String, isMovie: Boolean): Flow<Detail>
}