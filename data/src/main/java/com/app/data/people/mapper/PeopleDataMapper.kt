package com.app.data.people.mapper

import com.app.data.base.mapper.MapperData
import com.app.data.people.model.PeopleEntity
import com.app.data.people.model.PeoplePojo
import javax.inject.Inject

class PeopleDataMapper @Inject constructor() : MapperData<PeopleEntity, PeoplePojo> {

    override fun mapRemote(remotePojo: PeoplePojo): PeopleEntity {
        return PeopleEntity(
            id = remotePojo.id,
            name = remotePojo.name,
            originalName = remotePojo.original_name,
            mediaType = remotePojo.media_type,
            adult = remotePojo.adult,
            popularity = remotePojo.popularity,
            gender = remotePojo.gender,
            knownForDepartment = remotePojo.known_for_department,
            profilePath = "$IMAGE_URL${remotePojo.profile_path}"
        )
    }

    companion object {
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    }
}