package com.example.data.data

import com.example.data.myUser
import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.repository.ProfileRepo

class ProfileRepoImpl : ProfileRepo {
    override fun getProfileData(): ProfileData {
        return myUser
    }

    override fun saveProfileData(profileData: ProfileData) {
        myUser = profileData
    }
}