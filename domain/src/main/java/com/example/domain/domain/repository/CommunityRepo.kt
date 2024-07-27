package com.example.domain.domain.repository

import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData

interface CommunityRepo {
    suspend fun getCommunity(): CommunityData
    suspend fun getCommunities(): List<CommunityData>
    suspend fun getCommunityMeetings(): List<MeetingData>
}