package com.app.domain.people.uc
import com.app.domain.people.PeopleRepository
import com.app.domain.people.model.People
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetTrendingPeopleUC @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    suspend fun invoke(): Flow<List<People>> {
        return peopleRepository.getAll()
    }
    suspend fun refresh() {
        peopleRepository.refresh()
    }
}