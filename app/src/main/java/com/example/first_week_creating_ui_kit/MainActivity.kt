package com.example.first_week_creating_ui_kit

import ShowNumberPicker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.first_week_creating_ui_kit.navigation.RootScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.autorization.base.PasswordPreview
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




