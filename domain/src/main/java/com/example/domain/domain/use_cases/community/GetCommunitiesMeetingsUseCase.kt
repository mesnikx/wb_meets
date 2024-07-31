package com.example.domain.domain.use_cases.community

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.CommunityRepo

internal class GetCommunitiesMeetingsUseCaseImpl(private val repo: CommunityRepo) : GetCommunitiesMeetingsUseCase {
    override suspend fun execute(): List<MeetingData> = repo.getCommunityMeetings()
}

interface GetCommunitiesMeetingsUseCase {
    suspend fun execute(): List<MeetingData>
}