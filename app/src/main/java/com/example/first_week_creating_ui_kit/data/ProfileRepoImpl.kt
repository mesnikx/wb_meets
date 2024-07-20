package com.example.first_week_creating_ui_kit.data

import com.example.first_week_creating_ui_kit.domain.data.ProfileData
import com.example.first_week_creating_ui_kit.domain.repository.ProfileRepo
import com.example.first_week_creating_ui_kit.ui.utils.myUser

class ProfileRepoImpl : ProfileRepo {
    override fun getProfileData(): ProfileData {
        return myUser
    }

    override fun saveProfileData(profileData: ProfileData) {
        myUser = profileData
    }
}