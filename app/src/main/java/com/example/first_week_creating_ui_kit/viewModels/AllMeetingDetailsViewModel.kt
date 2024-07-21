package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo

class AllMeetingDetailsViewModel(
    private val repository: MeetingRepo
) : ViewModel() {
    private val _meetings = repository.getAllMeetings()
    val meetings = _meetings
    private val _card = mutableStateOf(MeetingData.getDefault())
    val card: State<MeetingData> = _card

    fun initializeAllId(cardId: String) {
        getAllMeetingById(cardId)?.let { _card.value = it }
    }

    private fun getAllMeetingById(meetingId: String): MeetingData? {
        return meetings.firstOrNull { it.meetingId == meetingId }
    }
}