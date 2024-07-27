package com.example.data.di

import com.example.data.data.CommunityRepoImpl
import com.example.data.data.MeetingRepoImpl
import com.example.data.data.ProfileRepoImpl
import com.example.domain.domain.repository.CommunityRepo
import com.example.domain.domain.repository.MeetingRepo
import com.example.domain.domain.repository.ProfileRepo
import org.koin.dsl.module

val dataModule = module {
    single<CommunityRepo> {
        CommunityRepoImpl()
    }
    single<MeetingRepo> {
        MeetingRepoImpl()
    }
    single<ProfileRepo> {
        ProfileRepoImpl()
    }
}