package com.example.first_week_creating_ui_kit.navigation.utils

import androidx.navigation.NavController
import com.example.first_week_creating_ui_kit.navigation.Routes

class Navigator(
    private val navController: NavController
) {
    fun navigateAllMeetingScreen() {
        navController.navigate(Routes.AllMeeting.SCREEN_ROUTE)
    }
    fun navigateCommunityScreen() {
        navController.navigate(Routes.Community.SCREEN_ROUTE)
    }
    fun navigateMoreScreen() {
        navController.navigate(Routes.More.SCREEN_ROUTE_MORE)
    }
    fun navigateToEnterCodeScreen() {
        navController.navigate(Routes.AuthorizationScreen.SCREEN_CODE_NUMBER_ROUTE)
    }
    fun back() {
        navController.popBackStack()
    }
    fun navigateToProfileScreen() {
        navController.navigate(Routes.More.SCREEN_ROUTE_PROFILE)
    }
    fun navigateToMyMeetingsScreen() {
        navController.navigate(Routes.More.SCREEN_ROUTE_MY_MEETING)
    }
}