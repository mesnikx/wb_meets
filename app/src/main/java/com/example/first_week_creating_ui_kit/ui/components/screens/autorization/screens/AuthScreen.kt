package com.example.first_week_creating_ui_kit.ui.components.screens.autorization.screens

import androidx.compose.runtime.Composable
import com.example.first_week_creating_ui_kit.viewModels.AuthScreens
import com.example.first_week_creating_ui_kit.viewModels.AuthorizationScreensViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(viewModel: AuthorizationScreensViewModel = koinViewModel()) {
    when(viewModel.currentScreen.value) {
        AuthScreens.EnterCodeScreen -> EnterCodeScreen(viewModel)
        AuthScreens.EnterPhoneNumberScreen -> EnterPhoneNumberScreen(viewModel)
        AuthScreens.EnterProfileDataScreen -> EnterProfileData(viewModel)
    }
}