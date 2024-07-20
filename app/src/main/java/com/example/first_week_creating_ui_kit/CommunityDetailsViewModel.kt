package com.example.first_week_creating_ui_kit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.first_week_creating_ui_kit.domain.data.CommunityData
import com.example.first_week_creating_ui_kit.domain.data.MeetingData
import com.example.first_week_creating_ui_kit.ui.utils.communityDataLists
import com.example.first_week_creating_ui_kit.ui.utils.meetingDataLists

class CommunityDetailsViewModel : ViewModel() {
    private val allCommunityList = communityDataLists
    private val allMeetingList = meetingDataLists

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
        return allCommunityList.firstOrNull { it.communityId == communityId }
    }
}