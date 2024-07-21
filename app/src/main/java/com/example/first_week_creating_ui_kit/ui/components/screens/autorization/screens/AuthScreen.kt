package com.example.first_week_creating_ui_kit.ui.components.screens.autorization.screens

import androidx.compose.runtime.Composable
import com.example.first_week_creating_ui_kit.viewModels.AuthScreens
import com.example.first_week_creating_ui_kit.viewModels.AuthorizationScreensViewModel

@Composable
fun AuthScreen(koinViewModel: AuthorizationScreensViewModel) {
    when(koinViewModel.currentScreen.value) {
        AuthScreens.EnterCodeScreen -> EnterCodeScreen(koinViewModel)
        AuthScreens.EnterPhoneNumberScreen -> EnterPhoneNumberScreen(koinViewModel)
        AuthScreens.EnterProfileDataScreen -> EnterProfileData(koinViewModel)
    }
}