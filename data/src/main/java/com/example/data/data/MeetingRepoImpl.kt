package com.example.data.data

import com.example.data.meetingDataLists
import com.example.data.myMeetingDataLists
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo

class MeetingRepoImpl : MeetingRepo {
    override fun getAllMeetings(): List<MeetingData> {
        return meetingDataLists
    }

    override fun getAllMeeting(): MeetingData {
        return MeetingData.getDefault()
    }

    override fun getMyMeetings(): List<MeetingData> {
        return myMeetingDataLists
    }

    override fun getMyMeeting(): MeetingData {
        return MeetingData.getDefault()
    }

}