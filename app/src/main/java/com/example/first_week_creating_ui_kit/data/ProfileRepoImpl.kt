package com.example.first_week_creating_ui_kit.data

import com.example.first_week_creating_ui_kit.domain.data.ProfileData
import com.example.first_week_creating_ui_kit.domain.repository.ProfileRepo

class ProfileRepoImpl: ProfileRepo {
    override fun getProfileData(): ProfileData {
        return ProfileData.getDefault()
    }
}