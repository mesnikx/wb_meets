package com.example.data.data

import com.example.data.communityDataLists
import com.example.data.meetingDataLists
import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.CommunityRepo

class CommunityRepoImpl :  CommunityRepo {
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