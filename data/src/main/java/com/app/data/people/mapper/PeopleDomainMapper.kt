package com.app.data.people.mapper

import com.app.data.base.mapper.MapperDomain
import com.app.data.movies.model.MovieEntity
import com.app.data.movies.model.MoviePojo
import com.app.data.people.model.PeopleEntity
import com.app.domain.movies.model.Movie
import com.app.domain.people.model.People
import javax.inject.Inject

class PeopleDomainMapper @Inject constructor() : MapperDomain<People, PeopleEntity> {
    override fun mapRemote(entity: PeopleEntity): People {
        return People(
            entity.id,
            entity.name,
            entity.originalName,
            entity.mediaType,
            entity.adult,
            entity.popularity,
            entity.gender,
            entity.knownForDepartment,
            entity.profilePath
        )
    }
}