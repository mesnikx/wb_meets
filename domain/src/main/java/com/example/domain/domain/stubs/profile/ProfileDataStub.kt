package com.example.domain.domain.stubs.profile

import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.repository.ProfileRepo
import com.example.domain.domain.utils.myUser

class ProfileDataStub : ProfileRepo {
    override suspend fun getProfileData(): ProfileData {
        return myUser
    }
    override suspend fun saveProfileData(profileData: ProfileData) {
        myUser = profileData
    }
}