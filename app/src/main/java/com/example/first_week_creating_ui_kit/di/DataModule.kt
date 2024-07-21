package com.example.first_week_creating_ui_kit.di

import org.koin.dsl.module

val dataModule = module {
    single<com.example.domain.domain.repository.CommunityRepo> {
        com.example.data.data.CommunityRepoImpl()
    }
    single<com.example.domain.domain.repository.MeetingRepo> {
        com.example.data.data.MeetingRepoImpl()
    }
    single<com.example.domain.domain.repository.ProfileRepo> {
        com.example.data.data.ProfileRepoImpl()
    }
}