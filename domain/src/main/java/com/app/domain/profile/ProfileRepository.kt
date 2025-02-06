package com.app.domain.profile

import com.app.domain.profile.model.Profile

interface ProfileRepository {
    suspend fun saveProfile(profile: Profile)
    suspend fun getProfile(): Profile?
}