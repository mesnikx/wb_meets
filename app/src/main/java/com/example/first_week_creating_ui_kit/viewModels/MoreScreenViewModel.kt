package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.repository.ProfileRepo

class MoreScreenViewModel(
    private val repository: ProfileRepo
) : ViewModel() {
    private val _profileData = mutableStateOf(repository.getProfileData())
    val profileData: State<ProfileData> = _profileData

    init {
        _profileData.value = repository.getProfileData()
    }
}