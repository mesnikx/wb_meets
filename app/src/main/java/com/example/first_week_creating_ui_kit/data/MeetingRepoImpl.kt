package com.example.first_week_creating_ui_kit.data

import com.example.first_week_creating_ui_kit.domain.data.MeetingData
import com.example.first_week_creating_ui_kit.domain.repository.MeetingRepo
import com.example.first_week_creating_ui_kit.ui.utils.meetingDataLists
import com.example.first_week_creating_ui_kit.ui.utils.myMeetingDataLists

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