package com.example.first_week_creating_ui_kit.navigation

import androidx.annotation.StringRes
import com.example.firstweek_lessonfirst.R


sealed class BottomBar(
    val route: String,
    val icon: Int,
    @StringRes val iconName: Int,
    val routes: List<String>
) {
    data object AllMeetings : BottomBar(
        route = Routes.AllMeeting.SCREEN_ROUTE,
        icon = R.drawable.ic_nav_cup,
        iconName = R.string.bot_nav_meetings,
        routes = listOf(Routes.AllMeeting.SCREEN_ROUTE)
    )

    data object Community : BottomBar(
        route = Routes.Community.SCREEN_ROUTE,
        icon = R.drawable.ic_nav_community,
        iconName = R.string.bot_nav_community,
        routes = listOf(Routes.Community.SCREEN_ROUTE)
    )

    data object More : BottomBar(
        route = Routes.More.SCREEN_ROUTE_MORE,
        icon = R.drawable.ic_nav_more,
        iconName = R.string.bot_nav_more,
        routes = listOf(
            Routes.More.SCREEN_ROUTE_MORE,
            Routes.More.SCREEN_ROUTE_PROFILE,
            Routes.More.SCREE_ROUTE_MY_MEETING
        )
    )
}


