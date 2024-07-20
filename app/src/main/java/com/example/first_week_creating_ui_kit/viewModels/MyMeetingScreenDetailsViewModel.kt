package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.first_week_creating_ui_kit.domain.data.MeetingData
import com.example.first_week_creating_ui_kit.domain.repository.MeetingRepo

class MyMeetingScreenDetailsViewModel(
    private val repository: MeetingRepo
): ViewModel() {
    private val _myMeetings = repository.getMyMeetings()
    val myMeetings = _myMeetings
    private val _card = mutableStateOf(MeetingData.getDefault())
    val card: State<MeetingData> = _card

    fun initializeMyId(cardId: String) {
        getMyMeetingById(cardId)?.let { _card.value = it }
    }

    private fun getMyMeetingById(meetingId: String): MeetingData? {
        return myMeetings.firstOrNull { it.meetingId == meetingId }
    }
}