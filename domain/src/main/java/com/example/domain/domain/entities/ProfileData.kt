package com.example.domain.domain.entities

import java.util.UUID

data class ProfileData(
    val userId: String = UUID.randomUUID().toString(),
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val imageProfile: String? = null,
) {
    companion object {
        fun getDefault(): ProfileData {
            return ProfileData(
                userId = "",
                name = "Иван",
                surname = "Иванов",
                phoneNumber = "+7 999 999-99-99",
                imageProfile = null,
            )
        }
    }
}
