package com.example.first_week_creating_ui_kit.navigation.utils

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf


val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("Local navigator not found")
}

val LocalSnackbarHost = staticCompositionLocalOf<SnackbarHostState> {
    error("Local navigator not found")
}
val LocalBottomBarState = staticCompositionLocalOf<BottomBarState> {
    error("Local bottom state not found")
}