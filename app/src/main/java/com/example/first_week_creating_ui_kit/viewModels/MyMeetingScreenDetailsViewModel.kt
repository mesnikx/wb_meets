package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyMeetingScreenDetailsViewModel(
    private val repository: com.example.domain.domain.repository.MeetingRepo
): ViewModel() {
    private val _myMeetings = repository.getMyMeetings()
    val myMeetings = _myMeetings
    private val _card = mutableStateOf(com.example.domain.domain.entities.MeetingData.getDefault())
    val card: State<com.example.domain.domain.entities.MeetingData> = _card

    fun initializeMyId(cardId: String) {
        getMyMeetingById(cardId)?.let { _card.value = it }
    }

    private fun getMyMeetingById(meetingId: String): com.example.domain.domain.entities.MeetingData? {
        return myMeetings.firstOrNull { it.meetingId == meetingId }
    }
}