package com.example.first_week_creating_ui_kit.domain.repository

import com.example.first_week_creating_ui_kit.domain.data.CommunityData
import com.example.first_week_creating_ui_kit.domain.data.MeetingData

interface CommunityRepo {
    fun getCommunity(): CommunityData
    fun getCommunities(): List<CommunityData>
    fun getCommunityMeetings(): List<MeetingData>
}