package com.example.first_week_creating_ui_kit.ui.utils

data class ProfileData(
    val name: String,
    val surname: String,
    val phoneNumber: String = "+7 999 999-99-99",
    val imageProfile: String? = null,
)
