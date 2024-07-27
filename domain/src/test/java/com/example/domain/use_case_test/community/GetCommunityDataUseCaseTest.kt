package com.example.domain.use_case_test.community

import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.CommunityRepo
import com.example.domain.domain.stubs.community.CommunityStub
import com.example.domain.domain.use_cases.community.GetCommunityDataUseCase
import com.example.domain.domain.use_cases.community.GetCommunityDataUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetCommunityDataUseCaseTest {

    private lateinit var communityStub: CommunityRepo
    private lateinit var getCommunityDataUseCase: GetCommunityDataUseCase

    @BeforeEach
    fun setUp() {
        communityStub = CommunityStub()
        getCommunityDataUseCase = GetCommunityDataUseCaseImpl(communityStub)
    }

    @Test
    fun `test returns default community data`() = runTest {
        val result = getCommunityDataUseCase.execute()
        assertEquals(CommunityData.getDefault(), result)
    }
    
    @Test
    fun `test community data correctly handles missing optional fields`() = runTest {
        val stub = object : CommunityRepo {
            override suspend fun getCommunity(): CommunityData = CommunityData(
                communityId = "1",
                title = "Test Community",
                description = null,
                numberOfSubs = 10,
                meetingData = emptyList()
            )

            override suspend fun getCommunities(): List<CommunityData> = emptyList()
            override suspend fun getCommunityMeetings(): List<MeetingData> = emptyList()
        }
        val useCase = GetCommunityDataUseCaseImpl(stub)
        val result = useCase.execute()
        assertNull(result.description)
    }
}