import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.first_week_creating_ui_kit.navigation.Routes
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.firstweek_lessonfirst.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.neutralColorBackground)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.meetings_animation))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            progress = { logoAnimationState.progress }
        )
        LaunchedEffect(Unit) {
            delay(3000)
            navController.navigate(Routes.AllMeeting.SCREEN_ROUTE) {
                popUpTo(Routes.LottieSplashScreen.SCREEN_ROUTE) { inclusive = true }
            }
        }
    }
}