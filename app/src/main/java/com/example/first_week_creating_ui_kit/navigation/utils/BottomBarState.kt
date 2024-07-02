package com.example.first_week_creating_ui_kit.navigation.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class BottomBarState(
    var isVisible: MutableState<Boolean> = mutableStateOf(true)
)