package com.example.first_week_creating_ui_kit.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val padding2dp: Dp,
    val padding4dp: Dp,
    val padding8dp: Dp,
    val padding12dp: Dp,
    val padding16dp: Dp,
    val padding20dp: Dp,
    val padding24dp: Dp
)

internal val dimensions = Dimens(
    padding2dp = 2.dp,
    padding4dp = 4.dp,
    padding8dp = 8.dp,
    padding12dp = 12.dp,
    padding16dp = 16.dp,
    padding20dp = 20.dp,
    padding24dp = 24.dp,
)
