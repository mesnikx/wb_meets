package com.example.domain.use_case_test.community

import com.example.domain.domain.stubs.community.CommunityStub
import com.example.domain.domain.use_cases.community.GetCommunitiesMeetingsUseCase
import com.example.domain.domain.use_cases.community.GetCommunitiesMeetingsUseCaseImpl
import com.example.domain.domain.utils.meetingDataLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetCommunitiesMeetingsUseCaseTest {

    private lateinit var communityStub: CommunityStub
    private lateinit var getCommunitiesMeetingsUseCase: GetCommunitiesMeetingsUseCase

    @BeforeEach
    fun setUp() {
        communityStub = CommunityStub()
        getCommunitiesMeetingsUseCase = GetCommunitiesMeetingsUseCaseImpl(communityStub)
    }

    @Test
    fun `test returns list of meetings`() = runTest {
        val result = getCommunitiesMeetingsUseCase.execute()
        assertEquals(meetingDataLists, result)
    }
}