package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AllMeetingDetailsViewModel(
    private val repository: com.example.domain.domain.repository.MeetingRepo
) : ViewModel() {
    private val _meetings = repository.getAllMeetings()
    val meetings = _meetings
    private val _card = mutableStateOf(com.example.domain.domain.entities.MeetingData.getDefault())
    val card: State<com.example.domain.domain.entities.MeetingData> = _card

    fun initializeAllId(cardId: String) {
        getAllMeetingById(cardId)?.let { _card.value = it }
    }

    private fun getAllMeetingById(meetingId: String): com.example.domain.domain.entities.MeetingData? {
        return meetings.firstOrNull { it.meetingId == meetingId }
    }
}