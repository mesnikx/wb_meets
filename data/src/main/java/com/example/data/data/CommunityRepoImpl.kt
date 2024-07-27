package com.example.data.data

import com.example.data.communityDataLists
import com.example.data.meetingDataLists
import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.CommunityRepo

internal class CommunityRepoImpl :  CommunityRepo {
    override suspend fun getCommunity(): CommunityData {
        return CommunityData.getDefault()
    }

    override suspend fun getCommunities(): List<CommunityData> {
        return communityDataLists
    }

    override suspend fun getCommunityMeetings(): List<MeetingData> {
        return meetingDataLists
    }

}