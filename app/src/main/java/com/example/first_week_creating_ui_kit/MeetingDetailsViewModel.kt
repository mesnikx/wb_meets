package com.example.first_week_creating_ui_kit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.first_week_creating_ui_kit.ui.utils.Meeting
import com.example.first_week_creating_ui_kit.ui.utils.meetingList
import com.example.first_week_creating_ui_kit.ui.utils.myMeetingList

class MeetingDetailsViewModel : ViewModel() {
    private val meetings = meetingList
    private val myMeetings = myMeetingList
    private val _card = mutableStateOf(Meeting.getDefault())

    val card: State<Meeting> = _card

    fun initializeAllId(cardId: String) {
        getAllMeetingById(cardId)?.let { _card.value = it }
    }
    fun initializeMyId(cardId: String) {
        getMyMeetingById(cardId)?.let { _card.value = it }
    }

    private fun getAllMeetingById(meetingId: String): Meeting? {
        return meetings.firstOrNull { it.meetingId == meetingId }
    }
    private fun getMyMeetingById(meetingId: String): Meeting? {
        return myMeetings.firstOrNull { it.meetingId == meetingId }
    }
}