package com.example.domain.domain.stubs.community

import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.CommunityRepo
import com.example.domain.domain.utils.communityDataLists
import com.example.domain.domain.utils.meetingDataLists

class CommunityStub : CommunityRepo {
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