package com.example.first_week_creating_ui_kit.domain.repository

import com.example.first_week_creating_ui_kit.domain.data.MeetingData

interface MeetingRepo {
    fun getMeetings(): List<MeetingData>
    fun getMeeting(): MeetingData
}