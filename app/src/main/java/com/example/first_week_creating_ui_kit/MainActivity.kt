package com.example.first_week_creating_ui_kit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.first_week_creating_ui_kit.navigation.RootScreen
import com.example.first_week_creating_ui_kit.ui.components.atoms.MyApp2
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowCardCommunity
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowCardMeeting
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowLineWithPeople
import com.example.first_week_creating_ui_kit.ui.components.screens.AllMeetingScreen
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                RootScreen()
            }
        }
    }
}




