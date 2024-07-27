package com.example.data.data

import com.example.data.communityDataLists
import com.example.data.meetingDataLists
import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.CommunityRepo

internal class CommunityRepoImpl : CommunityRepo {
    override suspend fun getCommunity(): CommunityData = CommunityData.getDefault()

    override suspend fun getCommunities(): List<CommunityData> = communityDataLists

    override suspend fun getCommunityMeetings(): List<MeetingData> = meetingDataLists

}