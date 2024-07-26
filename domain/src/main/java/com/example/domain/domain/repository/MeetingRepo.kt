package com.example.domain.domain.repository

import com.example.domain.domain.entities.MeetingData

interface MeetingRepo {
    fun getAllMeetings(): List<MeetingData>
    fun getAllMeeting(): MeetingData
    fun getMyMeetings(): List<MeetingData>
    fun getMyMeeting(): MeetingData
}