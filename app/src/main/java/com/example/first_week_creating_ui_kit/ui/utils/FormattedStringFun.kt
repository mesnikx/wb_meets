package com.example.first_week_creating_ui_kit.ui.utils

import java.text.DecimalFormat

fun Int.toFormattedString(): String {
    val decimalFormat = DecimalFormat("#,###")
    decimalFormat.decimalFormatSymbols = decimalFormat.decimalFormatSymbols.apply {
        groupingSeparator = ' '
    }
    return decimalFormat.format(this)
}