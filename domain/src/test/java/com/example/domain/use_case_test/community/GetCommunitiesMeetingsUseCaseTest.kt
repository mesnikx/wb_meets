package com.example.domain.use_case_test.community

import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.CommunityRepo
import com.example.domain.domain.stubs.community.CommunityStub
import com.example.domain.domain.use_cases.community.GetCommunitiesMeetingsUseCase
import com.example.domain.domain.use_cases.community.GetCommunitiesMeetingsUseCaseImpl
import com.example.domain.domain.utils.meetingDataLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetCommunitiesMeetingsUseCaseTest {

    private lateinit var communityStub: CommunityRepo
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

    @Test
    fun `test returns empty list when no meetings available`() = runTest {
        val emptyStub = object : CommunityRepo {
            override suspend fun getCommunityMeetings(): List<MeetingData> = emptyList()
            override suspend fun getCommunity(): CommunityData = CommunityData.getDefault()
            override suspend fun getCommunities(): List<CommunityData> = emptyList()
        }
        val useCase = GetCommunitiesMeetingsUseCaseImpl(emptyStub)
        val result = useCase.execute()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `test handle unexpected null response gracefully`() = runTest {
        val nullStub = object : CommunityRepo {
            override suspend fun getCommunityMeetings(): List<MeetingData> = emptyList()
            override suspend fun getCommunity(): CommunityData = CommunityData.getDefault()
            override suspend fun getCommunities(): List<CommunityData> = emptyList()
        }
        val useCase = GetCommunitiesMeetingsUseCaseImpl(nullStub)
        val result = useCase.execute()
        assertEquals(emptyList<MeetingData>(), result)
    }
}