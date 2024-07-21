package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CommunityDetailsViewModel(
    private val repository: com.example.domain.domain.repository.CommunityRepo
) : ViewModel() {
    private val allCommunityList = repository.getCommunities()
    private val allMeetingList = repository.getCommunityMeetings()

    private val _communityData = mutableStateOf(com.example.domain.domain.entities.CommunityData.getDefault())
    val communityData: State<com.example.domain.domain.entities.CommunityData> = _communityData

    private val _meetings = mutableStateOf<List<com.example.domain.domain.entities.MeetingData>>(emptyList())
    val meetings: State<List<com.example.domain.domain.entities.MeetingData>> = _meetings

    fun initializeCommunity(communityId: String) {
        val community = getCommunityById(communityId)
        if (community != null) {
            _communityData.value = community
            _meetings.value = getMeetingsForCommunity(communityId)
        }
    }

    private fun getMeetingsForCommunity(communityId: String): List<com.example.domain.domain.entities.MeetingData> {
        return allMeetingList.filter { it.communityDataId == communityId }
    }

    private fun getCommunityById(communityId: String): com.example.domain.domain.entities.CommunityData? {
        return allCommunityList.firstOrNull { it.communityId == communityId }
    }
}