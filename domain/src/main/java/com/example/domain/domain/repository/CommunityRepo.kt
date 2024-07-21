package com.example.domain.domain.repository

import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData

interface CommunityRepo {
    fun getCommunity(): CommunityData
    fun getCommunities(): List<CommunityData>
    fun getCommunityMeetings(): List<MeetingData>
}