package com.example.domain.domain.use_cases.profile

import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.repository.ProfileRepo

internal class GetProfileDataUseCaseImpl(private val repo: ProfileRepo) : GetProfileDataUseCase {
    override suspend fun execute(): ProfileData = repo.getProfileData()
}

interface GetProfileDataUseCase {
    suspend fun execute(): ProfileData
}