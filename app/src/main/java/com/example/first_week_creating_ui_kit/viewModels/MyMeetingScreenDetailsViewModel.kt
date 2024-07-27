package com.example.first_week_creating_ui_kit.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.use_cases.meeting.GetMyMeetingDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyMeetingScreenDetailsViewModel(
    private val getUseCase: GetMyMeetingDataUseCase
) : ViewModel() {
    private val _myMeetings = MutableStateFlow<List<MeetingData>>(emptyList())
    val myMeetings = _myMeetings
    private val _card = MutableStateFlow(MeetingData.getDefault())
    val card: StateFlow<MeetingData> = _card

    init {
        fetchMyMeetings()
    }

    private fun fetchMyMeetings() {
        viewModelScope.launch {
            try {
                val meetings = getUseCase.execute()
                _myMeetings.value = meetings
            } catch (e: Exception) {
                throw Exception()
            }
        }
    }

    fun initializeMyId(cardId: String) {
        viewModelScope.launch {
            val meeting = getMyMeetingById(cardId)
            meeting?.let { _card.value = it }
        }
    }

    private fun getMyMeetingById(meetingId: String): MeetingData? {
        return myMeetings.value.firstOrNull { it.meetingId == meetingId }
    }
}