package com.example.first_week_creating_ui_kit.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun CustomLottieAnimation(
    modifier: Modifier = Modifier,
    lottieSpec: LottieCompositionSpec,
    onAnimationEnd: () -> Unit
) {
    var hasAnimationEnded by remember { mutableStateOf(false) }

    val lottieComposition by rememberLottieComposition(lottieSpec)

    val animationProgress by animateLottieCompositionAsState(
        lottieComposition,
        iterations = 1,
        isPlaying = true
    )

    if (animationProgress == 1f && !hasAnimationEnded) {
        hasAnimationEnded = true
        onAnimationEnd()
    }

    LottieAnimation(
        composition = lottieComposition,
        progress = { animationProgress },
        modifier = modifier
    )
}