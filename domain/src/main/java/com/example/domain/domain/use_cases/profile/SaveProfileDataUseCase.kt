package com.example.domain.domain.use_cases.profile

import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.repository.ProfileRepo

internal class SaveProfileDataUseCaseImpl(private val repo: ProfileRepo) : SaveProfileDataUseCase {
    override suspend fun execute(profileData: ProfileData) = repo.saveProfileData(profileData)
}

interface SaveProfileDataUseCase {
    suspend fun execute(profileData: ProfileData)
}