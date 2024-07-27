package com.example.first_week_creating_ui_kit.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.use_cases.meeting.GetAllMeetingDataUseCase
import com.example.domain.domain.use_cases.meeting.GetAllMeetingListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AllMeetingDetailsViewModel(
    private val useCase: GetAllMeetingListUseCase
) : ViewModel() {
    private val _meetings = MutableStateFlow<List<MeetingData>>(emptyList())
    val meetings = _meetings
    private val _card = MutableStateFlow(MeetingData.getDefault())
    val card: StateFlow<MeetingData> = _card

    init {
        fetchAllMeetings()
    }

    private fun fetchAllMeetings() {
        viewModelScope.launch {
            _meetings.value = useCase.execute()
        }
    }

    fun initializeAllId(cardId: String) {
        viewModelScope.launch {
            val meeting = meetings.value.firstOrNull { it.meetingId == cardId }
            meeting?.let { _card.update { it } }
        }
    }
}