package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MoreScreenViewModel(
    private val repository: com.example.domain.domain.repository.ProfileRepo
) : ViewModel() {
    private val _profileData = mutableStateOf(repository.getProfileData())
    val profileData: State<com.example.domain.domain.entities.ProfileData> = _profileData

    init {
        _profileData.value = repository.getProfileData()
    }
}