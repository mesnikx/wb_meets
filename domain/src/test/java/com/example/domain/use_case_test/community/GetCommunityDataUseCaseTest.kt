package com.example.domain.use_case_test.community

import com.example.domain.domain.entities.CommunityData
import com.example.domain.domain.stubs.community.CommunityStub
import com.example.domain.domain.use_cases.community.GetCommunityDataUseCase
import com.example.domain.domain.use_cases.community.GetCommunityDataUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetCommunityDataUseCaseTest {

    private lateinit var communityStub: CommunityStub
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
}