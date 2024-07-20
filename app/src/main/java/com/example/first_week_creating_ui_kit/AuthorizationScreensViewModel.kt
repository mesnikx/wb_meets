package com.example.first_week_creating_ui_kit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.first_week_creating_ui_kit.domain.data.ProfileData
import com.example.first_week_creating_ui_kit.ui.utils.Country

class AuthorizationScreensViewModel : ViewModel() {
    private var _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber
    private var _fullPhoneNumber = mutableStateOf("")
    val fullPhoneNumber: State<String> = _fullPhoneNumber
    private var _selectedCountry = mutableStateOf(Country.countries[0])
    val selectedCountry: State<Country> = _selectedCountry
    private val _profileData = mutableStateOf(ProfileData.getDefault())
    val profileData: State<ProfileData> = _profileData

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