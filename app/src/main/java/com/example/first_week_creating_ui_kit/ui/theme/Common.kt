package com.example.first_week_creating_ui_kit.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class Colors(
    val brandColorDefault: Color,
    val brandColorDarkOnPressed: Color,
    val brandColorDarkMode: Color,
    val brandColorLight: Color,
    val brandColorBackground: Color,
    val neutralColorFont: Color,
    val neutralColorDark: Color,
    val neutralColorText: Color,
    val neutralColorSecondaryText: Color,
    val neutralColorDisabled: Color,
    val neutralColorDivider: Color,
    val neutralColorBackground: Color,
    val neutralColorSecondaryBackground: Color,
    val accentError: Color,
    val accentWarning: Color,
    val accentSuccess: Color,
    val accentSafe: Color,
    val gradient1: Brush,
    val gradient2: Brush,
    val gradientColorBackground: Color,
    val disabledColorForTab: Color
    )

enum class ColorStyle() {
    Base
}

object AppTheme {
    val dimens: Dimens
        @Composable
        get() = LocalDimens.current

    val colors: Colors
        @Composable
        get() = LocalColors.current

    val typo: AppTypo
        @Composable
        get() = LocalTypo.current
}

internal val LocalDimens = staticCompositionLocalOf { dimensions }
internal val LocalColors = staticCompositionLocalOf { baseLightPalette }
internal val LocalTypo = staticCompositionLocalOf { AppTypo }