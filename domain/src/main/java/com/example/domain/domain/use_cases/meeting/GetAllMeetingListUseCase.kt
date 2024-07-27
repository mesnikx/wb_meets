package com.example.domain.domain.use_cases.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo

internal class GetAllMeetingListUseCaseImpl(private val repo: MeetingRepo) : GetAllMeetingListUseCase {
    override suspend fun execute(): List<MeetingData> = repo.getAllMeetings()
}

interface GetAllMeetingListUseCase {
    suspend fun execute(): List<MeetingData>
}