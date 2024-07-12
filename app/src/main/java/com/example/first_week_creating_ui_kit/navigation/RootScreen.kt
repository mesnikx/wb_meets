package com.example.first_week_creating_ui_kit.navigation

import SplashScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.first_week_creating_ui_kit.AuthorizationScreensViewModel
import com.example.first_week_creating_ui_kit.CommunityDetailsViewModel
import com.example.first_week_creating_ui_kit.MeetingDetailsViewModel
import com.example.first_week_creating_ui_kit.navigation.utils.BottomBarState
import com.example.first_week_creating_ui_kit.navigation.utils.LocalBottomBarState
import com.example.first_week_creating_ui_kit.navigation.utils.LocalNavigator
import com.example.first_week_creating_ui_kit.navigation.utils.LocalSnackbarHost
import com.example.first_week_creating_ui_kit.navigation.utils.Navigator
import com.example.first_week_creating_ui_kit.ui.components.screens.allMeeting.AllMeetingScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.allMeeting.AllMeetingScreenDetails
import com.example.first_week_creating_ui_kit.ui.components.screens.autorization.screens.EnterCodeScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.autorization.screens.EnterPhoneNumberScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.community.CommunityScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.community.CommunityScreenDetails
import com.example.first_week_creating_ui_kit.ui.components.screens.more.MoreScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.more.MyMeetingScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.more.MyMeetingScreenDetails
import com.example.first_week_creating_ui_kit.ui.components.screens.more.ProfileScreen
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.bottomNavBarSize
import com.example.firstweek_lessonfirst.R


@Composable
fun BottomNavigationBar(navController: NavController, screens: List<BottomNavMenuItem>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            val selected =
                currentDestination?.hierarchy?.any {
                    it.route in screen.routes
                } == true
            BottomNavigationItem(
                modifier = Modifier
                    .background(AppTheme.colors.neutralColorBackground),
                selected = selected,
                label = {
                    if (selected) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = screen.iconName),
                                color = AppTheme.colors.neutralColorFont,
                                style = AppTheme.typo.textForBottomBar
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_nav_dot),
                                contentDescription = null,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(top = AppTheme.dimens.paddingSmall)
                            )
                        }
                    }
                },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if (!selected) {
                        Image(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            modifier = Modifier.size(bottomNavBarSize.dp),
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, bottomBarState: BottomBarState) {
    NavHost(
        navController = navController,
        startDestination = Routes.LottieSplashScreen.SCREEN_ROUTE
    ) {
        composable(Routes.LottieSplashScreen.SCREEN_ROUTE) {
            bottomBarState.isVisible.value = false
            SplashScreen(navController)
        }
        composable(Routes.AuthorizationScreen.SCREEN_PHONE_NUMBER_ROUTE) {
            bottomBarState.isVisible.value = false
            val viewModel = AuthorizationScreensViewModel()
            EnterPhoneNumberScreen(viewModel)

        }
        composable(Routes.AuthorizationScreen.SCREEN_CODE_NUMBER_ROUTE) {
            bottomBarState.isVisible.value = false
            val viewModel = AuthorizationScreensViewModel()
            EnterCodeScreen(viewModel)

        }
        composable(Routes.AllMeeting.SCREEN_ROUTE) {
            bottomBarState.isVisible.value = true
            AllMeetingScreen(navController)
        }
        composable(Routes.Community.SCREEN_ROUTE) {
            bottomBarState.isVisible.value = true
            CommunityScreen(navController)
        }
        composable(Routes.More.SCREEN_ROUTE_MORE) {
            bottomBarState.isVisible.value = true
            MoreScreen()
        }
        composable(Routes.More.SCREEN_ROUTE_PROFILE) {
            bottomBarState.isVisible.value = true
            ProfileScreen()
        }
        composable(Routes.More.SCREEN_ROUTE_MY_MEETING) {
            bottomBarState.isVisible.value = true
            MyMeetingScreen(navController)
        }
        composable(
            route = "${Routes.AllMeeting.SCREEN_DETAIL_ROUTE}/{${Routes.AllMeeting.SCREEN_DETAIL_ID_KEY}}",
            arguments = listOf(navArgument(Routes.AllMeeting.SCREEN_DETAIL_ID_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            bottomBarState.isVisible.value = true
            val meetingId =
                backStackEntry.arguments?.getString(Routes.AllMeeting.SCREEN_DETAIL_ID_KEY)
                    ?: return@composable
            val viewModel = MeetingDetailsViewModel()
            viewModel.initializeAllId(meetingId)
            AllMeetingScreenDetails(viewModel, navController)
        }
        composable(
            route = "${Routes.Community.SCREEN_DETAIL_ROUTE}/{${Routes.Community.SCREEN_DETAIL_ID_KEY}}",
            arguments = listOf(navArgument(Routes.Community.SCREEN_DETAIL_ID_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            bottomBarState.isVisible.value = true
            val communityId =
                backStackEntry.arguments?.getString(Routes.Community.SCREEN_DETAIL_ID_KEY)
                    ?: return@composable
            val viewModel = CommunityDetailsViewModel()
            viewModel.initializeCommunity(communityId)
            CommunityScreenDetails(viewModel)
        }
        composable(
            route = "${Routes.More.SCREEN_DETAIL_ROUTE}/{${Routes.More.SCREEN_DETAIL_ID_KEY}}",
            arguments = listOf(navArgument(Routes.More.SCREEN_DETAIL_ID_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            bottomBarState.isVisible.value = true
            val meetingId =
                backStackEntry.arguments?.getString(Routes.More.SCREEN_DETAIL_ID_KEY)
                    ?: return@composable
            val viewModel = MeetingDetailsViewModel()
            viewModel.initializeMyId(meetingId)
            MyMeetingScreenDetails(viewModel)
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootScreenContent(
    navController: NavHostController,
    bottomBarState: BottomBarState,
    snackbarHostState: SnackbarHostState,
    screens: List<BottomNavMenuItem>
) {
    Scaffold(
        bottomBar = {
            if (bottomBarState.isVisible.value) {
                BottomNavigationBar(navController = navController, screens = screens)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        AppNavHost(navController = navController, bottomBarState)
    }
}

@Composable
fun RootScreen() {
    val navController = rememberNavController()
    val screens = listOf(
        BottomNavMenuItem.AllMeetings,
        BottomNavMenuItem.Community,
        BottomNavMenuItem.More
    )
    val navigator = Navigator(navController)
    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val bottomBarState by remember { mutableStateOf(BottomBarState()) }

    CompositionLocalProvider(
        LocalNavigator provides navigator,
        LocalSnackbarHost provides snackbarHostState,
        LocalBottomBarState provides bottomBarState
    ) {
        RootScreenContent(
            navController = navController,
            bottomBarState = bottomBarState,
            snackbarHostState = snackbarHostState,
            screens = screens
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowRootScreen() {
    RootScreen()
}
