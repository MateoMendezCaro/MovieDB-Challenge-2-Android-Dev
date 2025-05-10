package com.app.data.profile

import com.app.data.profile.local.ProfileDao
import com.app.data.profile.model.ProfileEntity
import com.app.domain.profile.ProfileRepository
import com.app.domain.profile.model.Profile

class ProfileRepositoryImpl(private val profileDao: ProfileDao) : ProfileRepository {
    override suspend fun saveProfile(profile: Profile) {
        profileDao.insertProfile(ProfileEntity(userName = profile.userName, profileImagePath = profile.profileImagePath))
    }

    override suspend fun getProfile(): Profile? {
        return profileDao.getProfile()?.let { Profile(it.userName, it.profileImagePath) }
    }
}