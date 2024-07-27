package com.example.domain.use_case_test.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.stubs.meeting.MeetingStub
import com.example.domain.domain.use_cases.meeting.GetAllMeetingDataUseCase
import com.example.domain.domain.use_cases.meeting.GetAllMeetingDataUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetAllMeetingDataUseCaseTest {

    private lateinit var meetingStub: MeetingStub
    private lateinit var getAllMeetingDataUseCase: GetAllMeetingDataUseCase

    @BeforeEach
    fun setUp() {
        meetingStub = MeetingStub()
        getAllMeetingDataUseCase = GetAllMeetingDataUseCaseImpl(meetingStub)
    }

    @Test
    fun `test returns default meeting data`() = runTest {
        val result = getAllMeetingDataUseCase.execute()
        assertEquals(MeetingData.getDefault(), result)
    }
}