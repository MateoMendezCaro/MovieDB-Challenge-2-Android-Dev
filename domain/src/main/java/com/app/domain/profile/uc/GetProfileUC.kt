package com.app.domain.profile.uc

import com.app.domain.profile.ProfileRepository
import com.app.domain.profile.model.Profile

class GetProfileUC(private val repository: ProfileRepository) {
    suspend fun execute(): Profile? {
        return repository.getProfile()
    }
}