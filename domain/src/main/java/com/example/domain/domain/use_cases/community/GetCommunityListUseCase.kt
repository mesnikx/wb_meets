package com.example.domain.domain.use_cases.community

import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.repository.CommunityRepo

internal class GetCommunityListUseCaseImpl(private val repo: CommunityRepo) : GetCommunityListUseCase {
    override suspend fun execute(): List<CommunityData> = repo.getCommunities()
}

interface GetCommunityListUseCase {
    suspend fun execute(): List<CommunityData>
}
