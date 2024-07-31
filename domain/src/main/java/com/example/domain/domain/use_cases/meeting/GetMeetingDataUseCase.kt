package com.example.domain.domain.use_cases.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo

internal class GetAllMeetingDataUseCaseImpl(private val repo: MeetingRepo) : GetAllMeetingDataUseCase {
    override suspend fun execute(): MeetingData = repo.getAllMeeting()
}

interface GetAllMeetingDataUseCase {
    suspend fun execute(): MeetingData
}
