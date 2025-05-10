package com.app.domain.profile.uc

import com.app.domain.profile.ProfileRepository
import com.app.domain.profile.model.Profile

class SaveProfileUC(private val repository: ProfileRepository) {
    suspend fun execute(profile: Profile) {
        repository.saveProfile(profile)
    }
}