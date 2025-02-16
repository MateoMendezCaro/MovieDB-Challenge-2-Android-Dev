package com.app.data.people.di

import com.app.data.people.PeopleRepositoryImpl
import com.app.data.people.local.PeopleDao
import com.app.data.people.local.PeopleLocalSource
import com.app.data.people.local.PeopleLocalSourceImpl
import com.app.data.people.mapper.PeopleDataMapper
import com.app.data.people.mapper.PeopleDomainMapper
import com.app.data.people.remote.PeopleApi
import com.app.data.people.remote.PeopleRemoteSource
import com.app.domain.people.PeopleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PeopleModule {

    @Provides
    fun providePeopleRepository(
        localSource: PeopleLocalSource,
        domainMapper: PeopleDomainMapper,
        dataMapper: PeopleDataMapper,
        remoteSource: PeopleRemoteSource
    ): PeopleRepository {
        return PeopleRepositoryImpl(
            localSource, domainMapper, dataMapper, remoteSource
        )
    }
}
@Module
@InstallIn(SingletonComponent::class)
object PeopleDataModule {

    @Provides
    @Singleton
    fun providePeopleLocalSource(
        dao: PeopleDao
    ): PeopleLocalSource {
        return PeopleLocalSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun providePeopleApi(
        retrofit: Retrofit
    ): PeopleApi {
        return retrofit.create(PeopleApi::class.java)
    }

    @Provides
    @Singleton
    fun providePeopleRemoteSource(
        api: PeopleApi
    ): PeopleRemoteSource {
        return PeopleRemoteSource(api)
    }
}