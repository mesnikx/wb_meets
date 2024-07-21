package com.example.first_week_creating_ui_kit.di

import com.example.first_week_creating_ui_kit.viewModels.AllMeetingDetailsViewModel
import com.example.first_week_creating_ui_kit.viewModels.AuthorizationScreensViewModel
import com.example.first_week_creating_ui_kit.viewModels.CommunityDetailsViewModel
import com.example.first_week_creating_ui_kit.viewModels.MoreScreenViewModel
import com.example.first_week_creating_ui_kit.viewModels.MyMeetingScreenDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<CommunityDetailsViewModel> {
        CommunityDetailsViewModel(get())
    }
    viewModel<AllMeetingDetailsViewModel> {
        AllMeetingDetailsViewModel(get())
    }
    viewModel<MyMeetingScreenDetailsViewModel> {
        MyMeetingScreenDetailsViewModel(get())
    }
    viewModel<AuthorizationScreensViewModel> {
        AuthorizationScreensViewModel(get())
    }
    viewModel<MoreScreenViewModel> {
        MoreScreenViewModel(get())
    }
}