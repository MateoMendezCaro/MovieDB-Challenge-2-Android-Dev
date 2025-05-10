package com.app.data.people
import com.app.data.base.repository.BaseRepositoryImpl
import com.app.data.people.local.PeopleLocalSource
import com.app.data.people.mapper.PeopleDataMapper
import com.app.data.people.mapper.PeopleDomainMapper
import com.app.data.people.model.PeopleEntity
import com.app.data.people.model.PeoplePojo
import com.app.data.people.remote.PeopleRemoteSource
import com.app.domain.people.PeopleRepository
import com.app.domain.people.model.People
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val localSource: PeopleLocalSource,
    private val domainMapper: PeopleDomainMapper,
    private val dataMapper: PeopleDataMapper,
    private val remoteSource: PeopleRemoteSource
) : PeopleRepository, BaseRepositoryImpl<People, PeopleEntity, PeoplePojo>(
    localSource,
    dataMapper,
    domainMapper
) {

    override fun getAll(): Flow<List<People>> {
        return operationGetAll.asFlow().map { entities ->
            entities.map { domainMapper.mapRemote(it) }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchAll(): List<PeoplePojo> {
        return remoteSource.getAllPeople()
    }
}