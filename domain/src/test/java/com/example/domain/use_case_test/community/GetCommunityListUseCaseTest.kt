package com.example.domain.use_case_test.community

import com.example.domain.domain.stubs.community.CommunityStub
import com.example.domain.domain.use_cases.community.GetCommunityListUseCase
import com.example.domain.domain.use_cases.community.GetCommunityListUseCaseImpl
import com.example.domain.domain.utils.communityDataLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetCommunityListUseCaseTest {

    private lateinit var communityStub: CommunityStub
    private lateinit var getCommunityListUseCase: GetCommunityListUseCase

    @BeforeEach
    fun setUp() {
        communityStub = CommunityStub()
        getCommunityListUseCase = GetCommunityListUseCaseImpl(communityStub)
    }

    @Test
    fun `test returns list of communities`() = runTest {
        val result = getCommunityListUseCase.execute()
        assertEquals(communityDataLists, result)
    }
}