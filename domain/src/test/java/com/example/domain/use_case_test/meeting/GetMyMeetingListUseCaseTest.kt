package com.example.domain.use_case_test.meeting

import com.example.domain.domain.entities.MeetingData
import com.example.domain.domain.repository.MeetingRepo
import com.example.domain.domain.stubs.meeting.MeetingStub
import com.example.domain.domain.use_cases.meeting.GetMyMeetingListUseCase
import com.example.domain.domain.use_cases.meeting.GetMyMeetingListUseCaseImpl
import com.example.domain.domain.utils.myMeetingDataLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetMyMeetingListUseCaseTest {

    private lateinit var meetingStub: MeetingRepo
    private lateinit var getMyMeetingListUseCase: GetMyMeetingListUseCase

    @BeforeEach
    fun setUp() {
        meetingStub = MeetingStub()
        getMyMeetingListUseCase = GetMyMeetingListUseCaseImpl(meetingStub)
    }

    @Test
    fun `test returns my meetings`() = runTest {
        val result = getMyMeetingListUseCase.execute()
        assertEquals(myMeetingDataLists, result)
    }

    @Test
    fun `test handle null or missing data gracefully`() = runTest {
        val nullStub = object : MeetingRepo {
            override suspend fun getMyMeetings(): List<MeetingData> = emptyList()
            override suspend fun getAllMeetings(): List<MeetingData> = emptyList()
            override suspend fun getAllMeeting(): MeetingData = MeetingData.getDefault()
            override suspend fun getMyMeeting(): MeetingData = MeetingData.getDefault()
        }
        val useCase = GetMyMeetingListUseCaseImpl(nullStub)
        val result = useCase.execute()
        assertEquals(emptyList<MeetingData>(), result)
    }
}