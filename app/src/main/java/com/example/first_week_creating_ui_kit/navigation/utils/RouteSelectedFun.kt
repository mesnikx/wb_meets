package com.example.first_week_creating_ui_kit.navigation.utils

fun isRouteSelected(currentRoute: String?, baseRoute: String): Boolean {
    return currentRoute?.startsWith(baseRoute) == true
}