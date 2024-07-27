package com.example.domain.use_case_test.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo
import com.example.domain.domain.stubs.meeting.MeetingStub
import com.example.domain.domain.use_cases.meeting.GetMyMeetingDataUseCase
import com.example.domain.domain.use_cases.meeting.GetMyMeetingDataUseCaseImpl
import com.example.domain.domain.utils.myMeetingDataLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetMyMeetingDataUseCaseTest {

    private lateinit var meetingStub: MeetingRepo
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

    @Test
    fun `test returns empty list when no meetings available`() = runTest {
        val emptyStub = object : MeetingRepo {
            override suspend fun getMyMeetings(): List<MeetingData> = emptyList()
            override suspend fun getAllMeetings(): List<MeetingData> = emptyList()
            override suspend fun getAllMeeting(): MeetingData = MeetingData.getDefault()
            override suspend fun getMyMeeting(): MeetingData = MeetingData.getDefault()
        }
        val useCase = GetMyMeetingDataUseCaseImpl(emptyStub)
        val result = useCase.execute()
        assertTrue(result.isEmpty())
    }
}