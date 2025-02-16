package com.app.domain.people

import com.app.domain.base.BaseRepository
import com.app.domain.people.model.People
import kotlinx.coroutines.flow.Flow

interface PeopleRepository : BaseRepository<People> {
    suspend fun getAll(): Flow<List<People>>
}

