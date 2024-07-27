package com.example.data.data

import com.example.data.meetingDataLists
import com.example.data.myMeetingDataLists
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo

internal class MeetingRepoImpl : MeetingRepo {
    override suspend fun getAllMeetings(): List<MeetingData> = meetingDataLists

    override suspend fun getAllMeeting(): MeetingData = MeetingData.getDefault()

    override suspend fun getMyMeetings(): List<MeetingData> = myMeetingDataLists

    override suspend fun getMyMeeting(): MeetingData = MeetingData.getDefault()

}