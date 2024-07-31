package com.example.first_week_creating_ui_kit.navigation

import androidx.annotation.StringRes
import com.example.firstweek_lessonfirst.R


sealed class BottomNavMenuItem(
    val route: String,
    val icon: Int,
    @StringRes val iconName: Int,
    val routes: List<String>
) {
    data object AllMeetings : BottomNavMenuItem(
        route = Routes.AllMeeting.SCREEN_ROUTE,
        icon = R.drawable.ic_nav_cup,
        iconName = R.string.bot_nav_meetings,
        routes = listOf(
            Routes.AllMeeting.SCREEN_ROUTE,
            Routes.AllMeeting.SCREEN_DETAIL_ROUTE
        )
    )

    data object Community : BottomNavMenuItem(
        route = Routes.Community.SCREEN_ROUTE,
        icon = R.drawable.ic_nav_community,
        iconName = R.string.bot_nav_community,
        routes = listOf(
            Routes.Community.SCREEN_ROUTE,
            Routes.Community.SCREEN_DETAIL_ROUTE
        )
    )

    data object More : BottomNavMenuItem(
        route = Routes.More.SCREEN_ROUTE_MORE,
        icon = R.drawable.ic_nav_more,
        iconName = R.string.bot_nav_more,
        routes = listOf(
            Routes.More.SCREEN_ROUTE_MORE,
            Routes.More.SCREEN_ROUTE_PROFILE,
            Routes.More.SCREEN_ROUTE_MY_MEETING
        )
    )
}


