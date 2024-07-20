package com.example.first_week_creating_ui_kit.di

import com.example.first_week_creating_ui_kit.AuthorizationScreensViewModel
import com.example.first_week_creating_ui_kit.CommunityDetailsViewModel
import com.example.first_week_creating_ui_kit.MeetingDetailsViewModel
import com.example.first_week_creating_ui_kit.data.CommunityRepoImpl
import com.example.first_week_creating_ui_kit.data.MeetingRepoImpl
import com.example.first_week_creating_ui_kit.data.ProfileRepoImpl
import com.example.first_week_creating_ui_kit.domain.repository.CommunityRepo
import com.example.first_week_creating_ui_kit.domain.repository.MeetingRepo
import com.example.first_week_creating_ui_kit.domain.repository.ProfileRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CommunityRepo> {
        CommunityRepoImpl()
    }
    single<MeetingRepo> {
        MeetingRepoImpl()
    }
    single<ProfileRepo> {
        ProfileRepoImpl()
    }
    viewModel<CommunityDetailsViewModel> {
        CommunityDetailsViewModel()
    }
    viewModel<MeetingDetailsViewModel> {
        MeetingDetailsViewModel()
    }
    viewModel<AuthorizationScreensViewModel> {
        AuthorizationScreensViewModel()
    }
}