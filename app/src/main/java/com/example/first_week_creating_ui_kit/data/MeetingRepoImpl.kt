package com.example.first_week_creating_ui_kit.data

import com.example.first_week_creating_ui_kit.domain.data.MeetingData
import com.example.first_week_creating_ui_kit.domain.repository.MeetingRepo
import com.example.first_week_creating_ui_kit.ui.utils.meetingDataLists

class MeetingRepoImpl : MeetingRepo {
    override fun getMeetings(): List<MeetingData> {
        return meetingDataLists
    }

    override fun getMeeting(): MeetingData {
        return MeetingData.getDefault()
    }
}