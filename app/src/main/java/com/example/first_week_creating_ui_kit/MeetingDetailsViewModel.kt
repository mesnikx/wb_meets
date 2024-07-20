package com.example.first_week_creating_ui_kit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.first_week_creating_ui_kit.domain.data.MeetingData
import com.example.first_week_creating_ui_kit.ui.utils.meetingDataLists
import com.example.first_week_creating_ui_kit.ui.utils.myMeetingDataLists

class MeetingDetailsViewModel : ViewModel() {
    private val meetings = meetingDataLists
    private val myMeetings = myMeetingDataLists
    private val _card = mutableStateOf(MeetingData.getDefault())

    val card: State<MeetingData> = _card

    fun initializeAllId(cardId: String) {
        getAllMeetingById(cardId)?.let { _card.value = it }
    }
    fun initializeMyId(cardId: String) {
        getMyMeetingById(cardId)?.let { _card.value = it }
    }

    private fun getAllMeetingById(meetingId: String): MeetingData? {
        return meetings.firstOrNull { it.meetingId == meetingId }
    }
    private fun getMyMeetingById(meetingId: String): MeetingData? {
        return myMeetings.firstOrNull { it.meetingId == meetingId }
    }
}