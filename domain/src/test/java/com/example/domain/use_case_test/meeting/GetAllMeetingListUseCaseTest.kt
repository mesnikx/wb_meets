package com.example.domain.use_case_test.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo
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

    private lateinit var meetingStub: MeetingRepo
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
    fun `test returns empty list when no meetings available`() = runTest {
        val emptyStub = object : MeetingRepo {
            override suspend fun getAllMeetings(): List<MeetingData> = emptyList()
            override suspend fun getAllMeeting(): MeetingData = MeetingData.getDefault()
            override suspend fun getMyMeetings(): List<MeetingData> = emptyList()
            override suspend fun getMyMeeting(): MeetingData = MeetingData.getDefault()
        }
        val useCase = GetAllMeetingListUseCaseImpl(emptyStub)
        val result = useCase.execute()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `test handle malformed meeting data`() = runTest {
        val malformedData = listOf(
            MeetingData(meetingId = "1", title = "", description = null, dateAndPlace = "", isOver = false),
            MeetingData(meetingId = "2", title = "Meeting 2", description = "Description 2", dateAndPlace = "", isOver = false)
        )

        val stub = object : MeetingRepo {
            override suspend fun getAllMeetings(): List<MeetingData> = malformedData
            override suspend fun getAllMeeting(): MeetingData = MeetingData.getDefault()
            override suspend fun getMyMeetings(): List<MeetingData> = emptyList()
            override suspend fun getMyMeeting(): MeetingData = MeetingData.getDefault()
        }
        val useCase = GetAllMeetingListUseCaseImpl(stub)
        val result = useCase.execute()
        assertEquals(malformedData, result)
    }
}