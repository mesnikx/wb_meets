package com.example.first_week_creating_ui_kit.ui.utils

import java.text.DecimalFormat

fun Int.toFormattedString(): String {
    val decimalFormat = DecimalFormat("#,###")
    decimalFormat.decimalFormatSymbols = decimalFormat.decimalFormatSymbols.apply {
        groupingSeparator = ' '
    }
    return decimalFormat.format(this)
}

fun formatPhoneNumber(phoneNumber: String): String {
    val regex = Regex("(\\+\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})")
    return regex.replace(phoneNumber, "$1 $2 $3-$4-$5")
}