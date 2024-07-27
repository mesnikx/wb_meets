package com.example.domain.use_case_test.meeting

import com.example.domain.domain.stubs.meeting.MeetingStub
import com.example.domain.domain.use_cases.meeting.GetMyMeetingDataUseCase
import com.example.domain.domain.use_cases.meeting.GetMyMeetingDataUseCaseImpl
import com.example.domain.domain.utils.myMeetingDataLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetMyMeetingDataUseCaseTest {

    private lateinit var meetingStub: MeetingStub
    private lateinit var getMyMeetingDataUseCase: GetMyMeetingDataUseCase

    @BeforeEach
    fun setUp() {
        meetingStub = MeetingStub()
        getMyMeetingDataUseCase = GetMyMeetingDataUseCaseImpl(meetingStub)
    }

    @Test
    fun `test returns my meeting data`() = runTest {
        val result = getMyMeetingDataUseCase.execute()
        assertEquals(myMeetingDataLists, result)
    }
}