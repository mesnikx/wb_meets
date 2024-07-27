package com.example.first_week_creating_ui_kit.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.domain.entities.ProfileData
import com.example.domain.domain.use_cases.profile.SaveProfileDataUseCase
import com.example.first_week_creating_ui_kit.ui.utils.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthorizationScreensViewModel(
    private val saveUseCase: SaveProfileDataUseCase

) : ViewModel() {
    private var _currentScreen = mutableStateOf(AuthScreens.EnterPhoneNumberScreen)
    val currentScreen = _currentScreen
    private var _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber
    private var _fullPhoneNumber = MutableStateFlow("")
    val fullPhoneNumber: StateFlow<String> = _fullPhoneNumber
    private var _selectedCountry = MutableStateFlow(Country.countries[0])
    val selectedCountry: StateFlow<Country> = _selectedCountry
    private val _profileData = MutableStateFlow(ProfileData.getDefault())
    val profileData: StateFlow<ProfileData> = _profileData

    suspend fun saveProfileData() {
        val profileData = profileData.value.copy(phoneNumber = fullPhoneNumber.value)
        saveUseCase.execute(profileData)
    }

    fun nextScreen(nextScreen: AuthScreens) {
        _currentScreen.value = nextScreen
    }

    fun updateProfile(name: String, surname: String) {
        _profileData.value = profileData.value.copy(name = name, surname = surname)
    }

    fun updatePhoneNumber(uploadedPhoneNumber: String) {
        _phoneNumber.value = uploadedPhoneNumber
        updateFullPhoneNumber()
    }

    private fun updateFullPhoneNumber() {
        _fullPhoneNumber.value = "${selectedCountry.value.code}${_phoneNumber.value}"
    }

    fun updateCountryCode(country: Country) {
        _selectedCountry.value = country
        updateFullPhoneNumber()
    }

}

enum class AuthScreens {
    EnterCodeScreen,
    EnterPhoneNumberScreen,
    EnterProfileDataScreen
}