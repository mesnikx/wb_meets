package com.example.domain.use_case_test.profile

import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.stubs.profile.ProfileDataStub
import com.example.domain.domain.use_cases.profile.GetProfileDataUseCase
import com.example.domain.domain.use_cases.profile.GetProfileDataUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetProfileDataUseCaseTest {

    private lateinit var profileRepoStub: ProfileDataStub
    private lateinit var getProfileDataUseCase: GetProfileDataUseCase

    @BeforeEach
    fun setUp() {
        profileRepoStub = ProfileDataStub()
        getProfileDataUseCase = GetProfileDataUseCaseImpl(profileRepoStub)
    }

    @Test
    fun `test returns updated profile data`() = runTest {
        val newProfileData = ProfileData(
            userId = "1",
            name = "John",
            surname = "Doe",
            phoneNumber = "+1 800 123 4567"
        )
        profileRepoStub.saveProfileData(newProfileData)

        val result = getProfileDataUseCase.execute()
        assertEquals(newProfileData, result)
    }
}