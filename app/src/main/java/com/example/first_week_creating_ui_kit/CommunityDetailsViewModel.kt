package com.example.first_week_creating_ui_kit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.first_week_creating_ui_kit.ui.utils.Community
import com.example.first_week_creating_ui_kit.ui.utils.Meeting
import com.example.first_week_creating_ui_kit.ui.utils.communityList
import com.example.first_week_creating_ui_kit.ui.utils.meetingList

class CommunityDetailsViewModel : ViewModel() {
    private val allCommunityList = communityList
    private val allMeetingList = meetingList

    private val _community = mutableStateOf(Community.getDefault())
    val community: State<Community> = _community

    private val _meetings = mutableStateOf<List<Meeting>>(emptyList())
    val meetings: State<List<Meeting>> = _meetings

    fun initializeCommunity(communityId: String) {
        val community = getCommunityById(communityId)
        if (community != null) {
            _community.value = community
            _meetings.value = getMeetingsForCommunity(communityId)
        }
    }

    private fun getMeetingsForCommunity(communityId: String): List<Meeting> {
        return allMeetingList.filter { it.communityId == communityId }
    }

    private fun getCommunityById(communityId: String): Community? {
        return allCommunityList.firstOrNull { it.communityId == communityId }
    }
}