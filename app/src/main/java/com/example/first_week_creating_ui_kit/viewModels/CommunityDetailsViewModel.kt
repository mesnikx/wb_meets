package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.CommunityRepo

class CommunityDetailsViewModel(
    private val repository: CommunityRepo
) : ViewModel() {
    private val _allCommunityList = repository.getCommunities()
    val allCommunityList = _allCommunityList
    private val allMeetingList = repository.getCommunityMeetings()

    private val _communityData = mutableStateOf(CommunityData.getDefault())
    val communityData: State<CommunityData> = _communityData

    private val _meetings = mutableStateOf<List<MeetingData>>(emptyList())
    val meetings: State<List<MeetingData>> = _meetings

    fun initializeCommunity(communityId: String) {
        val community = getCommunityById(communityId)
        if (community != null) {
            _communityData.value = community
            _meetings.value = getMeetingsForCommunity(communityId)
        }
    }

    private fun getMeetingsForCommunity(communityId: String): List<MeetingData> {
        return allMeetingList.filter { it.communityDataId == communityId }
    }

    private fun getCommunityById(communityId: String): CommunityData? {
        return _allCommunityList.firstOrNull { it.communityId == communityId }
    }
}