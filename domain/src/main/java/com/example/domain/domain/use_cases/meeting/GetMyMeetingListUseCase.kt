package com.example.domain.domain.use_cases.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo

internal class GetMyMeetingListUseCaseImpl(private val repo: MeetingRepo) : GetMyMeetingListUseCase {
    override suspend fun execute(): List<MeetingData> = repo.getMyMeetings()

}

interface GetMyMeetingListUseCase {
    suspend fun execute(): List<MeetingData>
}