package com.example.first_week_creating_ui_kit.data

import com.example.first_week_creating_ui_kit.domain.data.CommunityData
import com.example.first_week_creating_ui_kit.domain.data.MeetingData
import com.example.first_week_creating_ui_kit.domain.repository.CommunityRepo
import com.example.first_week_creating_ui_kit.ui.utils.communityDataLists
import com.example.first_week_creating_ui_kit.ui.utils.meetingDataLists

class CommunityRepoImpl : CommunityRepo {
    override fun getCommunity(): CommunityData {
        return CommunityData.getDefault()
    }

    override fun getCommunities(): List<CommunityData> {
        return communityDataLists
    }

    override fun getCommunityMeetings(): List<MeetingData> {
        return meetingDataLists
    }

}