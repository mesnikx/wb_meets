package com.example.domain.use_case_test.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo
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

    private lateinit var meetingStub: MeetingRepo
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

    @Test
    fun `test handle null or missing data gracefully`() = runTest {
        val nullStub = object : MeetingRepo {
            override suspend fun getAllMeeting(): MeetingData = MeetingData.getDefault()
            override suspend fun getAllMeetings(): List<MeetingData> = emptyList()
            override suspend fun getMyMeetings(): List<MeetingData> = emptyList()
            override suspend fun getMyMeeting(): MeetingData = MeetingData.getDefault()
        }
        val useCase = GetAllMeetingDataUseCaseImpl(nullStub)
        val result = useCase.execute()
        assertEquals(MeetingData.getDefault(), result)
    }
}