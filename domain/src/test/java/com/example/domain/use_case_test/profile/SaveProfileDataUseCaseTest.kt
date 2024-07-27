package com.example.domain.use_case_test.profile

import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.repository.ProfileRepo
import com.example.domain.domain.stubs.profile.ProfileDataStub
import com.example.domain.domain.use_cases.profile.GetProfileDataUseCase
import com.example.domain.domain.use_cases.profile.GetProfileDataUseCaseImpl
import com.example.domain.domain.use_cases.profile.SaveProfileDataUseCase
import com.example.domain.domain.use_cases.profile.SaveProfileDataUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class SaveProfileDataUseCaseTest {

    private lateinit var profileRepoStub: ProfileRepo
    private lateinit var getProfileDataUseCase: GetProfileDataUseCase
    private lateinit var saveProfileDataUseCase: SaveProfileDataUseCase

    @BeforeEach
    fun setUp() {
        profileRepoStub = ProfileDataStub()
        getProfileDataUseCase = GetProfileDataUseCaseImpl(profileRepoStub)
        saveProfileDataUseCase = SaveProfileDataUseCaseImpl(profileRepoStub)
    }


    @Test
    fun `test SaveProfileDataUseCase saves profile data correctly`() = runTest {
        val newProfileData = ProfileData(
            userId = "2",
            name = "Alice",
            surname = "Smith",
            phoneNumber = "+1 800 654 3210"
        )

        saveProfileDataUseCase.execute(newProfileData)

        val savedData = getProfileDataUseCase.execute()
        assertEquals(newProfileData, savedData)
    }

    @Test
    fun `test SaveProfileDataUseCase saves profile data with empty fields`() = runTest {
        val newProfileData = ProfileData(
            userId = "3",
            name = "",
            surname = "",
            phoneNumber = "+1 800 654 3210"
        )

        saveProfileDataUseCase.execute(newProfileData)

        val savedData = getProfileDataUseCase.execute()
        assertEquals(newProfileData, savedData)
    }
}