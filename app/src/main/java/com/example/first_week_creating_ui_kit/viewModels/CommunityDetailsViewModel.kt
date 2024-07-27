package com.example.first_week_creating_ui_kit.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.use_cases.community.GetCommunitiesMeetingsUseCase
import com.example.domain.domain.use_cases.community.GetCommunityListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityDetailsViewModel(
    private val getListUseCase: GetCommunityListUseCase,
    private val getMeetingListUseCase: GetCommunitiesMeetingsUseCase,
) : ViewModel() {
    private val _allCommunityList = MutableStateFlow<List<CommunityData>>(emptyList())
    val allCommunityList = _allCommunityList

    private val _allMeetingList = MutableStateFlow<List<MeetingData>>(emptyList())
    val allMeetingList = _allMeetingList

    private val _communityData = MutableStateFlow(CommunityData.getDefault())
    val communityData: StateFlow<CommunityData> = _communityData

    private val _meetings = MutableStateFlow<List<MeetingData>>(emptyList())
    val meetings: StateFlow<List<MeetingData>> = _meetings

    init {
        fetchAllCommunities()
    }

    private fun fetchAllCommunities() {
        viewModelScope.launch {
            _allCommunityList.value = getListUseCase.execute()
            _allMeetingList.value = getMeetingListUseCase.execute()
        }
    }

    fun initializeCommunity(communityId: String) {
        viewModelScope.launch {
            val community = getCommunityById(communityId)
            if (community != null) {
                _communityData.value = community
                _meetings.value = getMeetingsForCommunity(communityId)
            }
        }
    }

    private fun getMeetingsForCommunity(communityId: String): List<MeetingData> {
        return allMeetingList.value.filter { it.communityDataId == communityId }
    }

    private fun getCommunityById(communityId: String): CommunityData? {
        return _allCommunityList.value.firstOrNull() { it.communityId == communityId }
    }
}