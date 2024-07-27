package com.example.domain.domain.stubs.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo
import com.example.domain.domain.utils.meetingDataLists
import com.example.domain.domain.utils.myMeetingDataLists

class MeetingStub: MeetingRepo {
    override suspend fun getAllMeetings(): List<MeetingData> {
        return meetingDataLists
    }

    override suspend fun getAllMeeting(): MeetingData {
        return MeetingData.getDefault()
    }

    override suspend fun getMyMeetings(): List<MeetingData> {
        return myMeetingDataLists
    }

    override suspend fun getMyMeeting(): MeetingData {
        return MeetingData.getDefault()
    }
}