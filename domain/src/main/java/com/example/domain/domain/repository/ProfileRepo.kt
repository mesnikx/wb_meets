package com.example.domain.domain.repository

import com.example.domain.domain.entities.ProfileData

interface ProfileRepo {
    fun getProfileData(): ProfileData
    fun saveProfileData(profileData: ProfileData)
}