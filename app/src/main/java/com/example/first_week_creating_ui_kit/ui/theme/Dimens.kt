package com.example.first_week_creating_ui_kit.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val paddingXSmall: Dp,
    val paddingSmall: Dp,
    val paddingMedium: Dp,
    val paddingLarge: Dp,
    val paddingXLarge: Dp,
    val paddingXXLarge: Dp,
    val paddingXXXLarge: Dp
)

internal val dimensions = Dimens(
    paddingXSmall = 2.dp,
    paddingSmall = 4.dp,
    paddingMedium = 8.dp,
    paddingLarge = 12.dp,
    paddingXLarge = 16.dp,
    paddingXXLarge = 20.dp,
    paddingXXXLarge = 24.dp,
)
