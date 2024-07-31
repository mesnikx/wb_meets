package com.example.domain.domain.di

import com.example.domain.domain.use_cases.community.GetCommunitiesMeetingsUseCase
import com.example.domain.domain.use_cases.community.GetCommunitiesMeetingsUseCaseImpl
import com.example.domain.domain.use_cases.community.GetCommunityDataUseCase
import com.example.domain.domain.use_cases.community.GetCommunityDataUseCaseImpl
import com.example.domain.domain.use_cases.community.GetCommunityListUseCase
import com.example.domain.domain.use_cases.community.GetCommunityListUseCaseImpl
import com.example.domain.domain.use_cases.meeting.GetAllMeetingDataUseCase
import com.example.domain.domain.use_cases.meeting.GetAllMeetingDataUseCaseImpl
import com.example.domain.domain.use_cases.meeting.GetAllMeetingListUseCase
import com.example.domain.domain.use_cases.meeting.GetAllMeetingListUseCaseImpl
import com.example.domain.domain.use_cases.meeting.GetMyMeetingDataUseCase
import com.example.domain.domain.use_cases.meeting.GetMyMeetingDataUseCaseImpl
import com.example.domain.domain.use_cases.meeting.GetMyMeetingListUseCase
import com.example.domain.domain.use_cases.meeting.GetMyMeetingListUseCaseImpl
import com.example.domain.domain.use_cases.profile.GetProfileDataUseCase
import com.example.domain.domain.use_cases.profile.GetProfileDataUseCaseImpl
import com.example.domain.domain.use_cases.profile.SaveProfileDataUseCase
import com.example.domain.domain.use_cases.profile.SaveProfileDataUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<GetProfileDataUseCase> { GetProfileDataUseCaseImpl(get()) }
    single<SaveProfileDataUseCase> { SaveProfileDataUseCaseImpl(get()) }

    single<GetCommunitiesMeetingsUseCase> { GetCommunitiesMeetingsUseCaseImpl(get()) }
    single<GetCommunityDataUseCase> { GetCommunityDataUseCaseImpl(get()) }
    single<GetCommunityListUseCase> { GetCommunityListUseCaseImpl(get()) }

    single<GetAllMeetingDataUseCase> { GetAllMeetingDataUseCaseImpl(get()) }
    single<GetAllMeetingListUseCase> { GetAllMeetingListUseCaseImpl(get()) }
    single<GetMyMeetingDataUseCase> { GetMyMeetingDataUseCaseImpl(get()) }
    single<GetMyMeetingListUseCase> { GetMyMeetingListUseCaseImpl(get()) }

}