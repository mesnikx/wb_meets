package com.example.data.data

import com.example.data.myUser
import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.repository.ProfileRepo

internal class ProfileRepoImpl : ProfileRepo {
    override suspend fun getProfileData(): ProfileData {
        return myUser
    }

    override suspend fun saveProfileData(profileData: ProfileData) {
        myUser = profileData
    }
}