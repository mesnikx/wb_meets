package com.example.domain.domain.repository

import com.example.domain.domain.entities.MeetingData

interface MeetingRepo {
    suspend fun getAllMeetings(): List<MeetingData>
    suspend fun getAllMeeting(): MeetingData
    suspend fun getMyMeetings(): List<MeetingData>
    suspend fun getMyMeeting(): MeetingData
}