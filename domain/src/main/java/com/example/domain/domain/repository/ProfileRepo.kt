package com.example.domain.domain.repository

import com.example.domain.domain.entities.ProfileData

interface ProfileRepo {
    suspend fun getProfileData(): ProfileData
    suspend fun saveProfileData(profileData: ProfileData)
}