package com.example.domain.use_case_test.meeting

import com.example.domain.domain.stubs.meeting.MeetingStub
import com.example.domain.domain.use_cases.meeting.GetAllMeetingListUseCase
import com.example.domain.domain.use_cases.meeting.GetAllMeetingListUseCaseImpl
import com.example.domain.domain.utils.meetingDataLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetAllMeetingListUseCaseTest {

    private lateinit var meetingStub: MeetingStub
    private lateinit var getAllMeetingListUseCase: GetAllMeetingListUseCase

    @BeforeEach
    fun setUp() {
        meetingStub = MeetingStub()
        getAllMeetingListUseCase = GetAllMeetingListUseCaseImpl(meetingStub)
    }

    @Test
    fun `test returns all meetings`() = runTest {
        val result = getAllMeetingListUseCase.execute()
        assertEquals(meetingDataLists, result)
    }
    @Test
    fun `test returns empty list when no meetings`() = runTest {
        val result = getAllMeetingListUseCase.execute()
        assertTrue(result.isNotEmpty())
    }
}