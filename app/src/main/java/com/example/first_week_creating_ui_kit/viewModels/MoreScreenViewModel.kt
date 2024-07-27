package com.example.first_week_creating_ui_kit.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.use_cases.profile.GetProfileDataUseCase
import com.example.domain.domain.use_cases.profile.SaveProfileDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoreScreenViewModel(
    private val getUseCase: GetProfileDataUseCase,
    private val saveUseCase: SaveProfileDataUseCase
) : ViewModel() {
    private val _profileData = MutableStateFlow(ProfileData.getDefault())
    val profileData: StateFlow<ProfileData> = _profileData

    init {
        fetchProfileData()
    }

    private fun fetchProfileData() {
        viewModelScope.launch {
            try {
                val data = getUseCase.execute()
                _profileData.value = data
            } catch (e: Exception) {
                _profileData.value = ProfileData.getDefault()
            }
        }
    }

    fun saveProfileData(profileData: ProfileData) {
        viewModelScope.launch {
            try {
                saveUseCase.execute(profileData)
                _profileData.value = profileData
            } catch (e: Exception) {
                throw Exception()
            }
        }
    }
}