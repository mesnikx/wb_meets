package com.example.first_week_creating_ui_kit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(
    colorStyle: ColorStyle = ColorStyle.Base,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    when (colorStyle) {
        ColorStyle.Base -> baseLightPalette
    }

    CompositionLocalProvider(
        LocalColors provides baseLightPalette,
        LocalTypo provides AppTypo,
        LocalDimens provides dimensions,
        content = content
    )
}