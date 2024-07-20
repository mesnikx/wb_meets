package com.example.first_week_creating_ui_kit.domain.repository

import com.example.first_week_creating_ui_kit.domain.data.ProfileData

interface ProfileRepo {
    fun getProfileData(): ProfileData
}