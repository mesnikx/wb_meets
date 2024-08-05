import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.first_week_creating_ui_kit.navigation.Routes
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.CustomLottieAnimation
import com.example.firstweek_lessonfirst.R

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.neutralColorBackground),
        contentAlignment = Alignment.Center
    ) {
        CustomLottieAnimation(
            modifier = Modifier.fillMaxSize(),
            LottieCompositionSpec.RawRes(
                R.raw.meetings_animation
            ),
            onAnimationEnd = {
                navController.navigate(Routes.AuthorizationScreen.SCREEN_AUTH_ROUTE) {
                    popUpTo(Routes.LottieSplashScreen.SCREEN_ROUTE) { inclusive = true }
                }
            }
        )
    }
}