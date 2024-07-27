package com.example.domain.domain.use_cases.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo

internal class GetMyMeetingDataUseCaseImpl(private val repo: MeetingRepo) : GetMyMeetingDataUseCase {
    override suspend fun execute(): List<MeetingData> = repo.getMyMeetings()
}

interface GetMyMeetingDataUseCase {
    suspend fun execute(): List<MeetingData>
}