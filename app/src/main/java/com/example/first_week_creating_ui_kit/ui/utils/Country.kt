package com.example.first_week_creating_ui_kit.ui.utils

import com.example.firstweek_lessonfirst.R

data class Country(
    val code: String,
    val name: String,
    val countryVector: Int
) {
    companion object Countries {
        val countries = listOf(
            Country(
                code = "+7",
                name = "Россия",
                countryVector = R.drawable.flag_russian_federation
            ),
            Country(
                code = "+374",
                name = "Армения",
                countryVector = R.drawable.flag_armenia
            ),
            Country(
                code = "+994",
                name = "Азербайджан",
                countryVector = R.drawable.flag_azerbaijan
            ),
            Country(
                code = "+375",
                name = "Беларусь",
                countryVector = R.drawable.flag_belarus
            ),
            Country(
                code = "+995",
                name = "Грузия",
                countryVector = R.drawable.flag_georgia
            ),
            Country(
                code = "+86",
                name = "Китай",
                countryVector = R.drawable.flag_china
            ),
            Country(
                code = "+996",
                name = "Кыргызстан",
                countryVector = R.drawable.flag_kyrgyzstan
            ),
            Country(
                code = "+7",
                name = "Казахстан",
                countryVector = R.drawable.flag_kz
            ),
            Country(
                code = "+82",
                name = "Южная Корея",
                countryVector = R.drawable.flag_south_korea
            ),
            Country(
                code = "+90",
                name = "Турция",
                countryVector = R.drawable.flag_turkey
            ),
            Country(
                code = "+971",
                name = "ОАЭ",
                countryVector = R.drawable.flag_uae
            ),
            Country(
                code = "+44",
                name = "Великобритания",
                countryVector = R.drawable.flag_uk
            ),
            Country(
                code = "+1",
                name = "США",
                countryVector = R.drawable.flag_us
            ),
            Country(
                code = "+998",
                name = "Узбекистан",
                countryVector = R.drawable.flag_uzbekistan
            )
        )
    }
}
