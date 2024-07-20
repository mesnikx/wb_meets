package com.example.first_week_creating_ui_kit.domain.repository

import com.example.first_week_creating_ui_kit.domain.data.MeetingData

interface MeetingRepo {
    fun getAllMeetings(): List<MeetingData>
    fun getAllMeeting(): MeetingData
    fun getMyMeetings(): List<MeetingData>
    fun getMyMeeting(): MeetingData
}