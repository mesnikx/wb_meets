package com.example.first_week_creating_ui_kit.ui.components.screens.more

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.first_week_creating_ui_kit.MeetingDetailsViewModel
import com.example.first_week_creating_ui_kit.ui.components.screens.allMeeting.AllMeetingScreenDetails
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.Meeting
import com.example.firstweek_lessonfirst.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyMeetingScreenDetails(
    viewModel: MeetingDetailsViewModel
) {
    val card = viewModel.card.value
    if (card.meetingId != Meeting.getDefault().meetingId) {
        AllMeetingScreenDetails(viewModel = viewModel, navController = rememberNavController())
    } else {
        Text(
            text = stringResource(id = R.string.meeting_not_found),
            style = AppTheme.typo.h1
        )
    }
}