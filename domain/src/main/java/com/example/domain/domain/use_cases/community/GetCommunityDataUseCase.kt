package com.example.domain.domain.use_cases.community

import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.repository.CommunityRepo

internal class GetCommunityDataUseCaseImpl(private val repo: CommunityRepo) : GetCommunityDataUseCase{
    override suspend fun execute(): CommunityData = repo.getCommunity()
}

interface GetCommunityDataUseCase {
    suspend fun execute(): CommunityData
}
