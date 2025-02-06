package com.app.moviedb.profile.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.profile.model.Profile
import kotlinx.coroutines.launch
import com.app.domain.profile.uc.GetProfileUC
import com.app.domain.profile.uc.SaveProfileUC
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val saveProfileUseCase: SaveProfileUC,
    private val getProfileUseCase: GetProfileUC
) : ViewModel() {

    private val _profile = mutableStateOf<Profile?>(null)
    val profile: State<Profile?> get() = _profile

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _profile.value = getProfileUseCase.execute()
        }
    }

    fun updateProfile(name: String, imageUri: String) {
        viewModelScope.launch {
            val updatedProfile = Profile(name, imageUri)
            saveProfileUseCase.execute(updatedProfile)
            _profile.value = updatedProfile
        }
    }

    fun updateProfileImage(imageUri: Uri?) {
        _profile.value?.let { currentProfile ->
            updateProfile(currentProfile.userName, imageUri.toString())
        }
    }
}