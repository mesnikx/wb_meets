package com.example.first_week_creating_ui_kit.navigation

import SplashScreen
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.first_week_creating_ui_kit.navigation.utils.BottomBarState
import com.example.first_week_creating_ui_kit.navigation.utils.LocalBottomBarState
import com.example.first_week_creating_ui_kit.navigation.utils.LocalNavigator
import com.example.first_week_creating_ui_kit.navigation.utils.LocalSnackbarHost
import com.example.first_week_creating_ui_kit.navigation.utils.Navigator
import com.example.first_week_creating_ui_kit.navigation.utils.isRouteSelected
import com.example.first_week_creating_ui_kit.ui.components.screens.allMeeting.AllMeetingScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.allMeeting.AllMeetingScreenDetails
import com.example.first_week_creating_ui_kit.ui.components.screens.autorization.screens.AuthScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.community.CommunityScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.community.CommunityScreenDetails
import com.example.first_week_creating_ui_kit.ui.components.screens.more.MoreScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.more.MyMeetingScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.more.MyMeetingScreenDetails
import com.example.first_week_creating_ui_kit.ui.components.screens.more.ProfileScreen
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.viewModels.AllMeetingDetailsViewModel
import com.example.first_week_creating_ui_kit.viewModels.AuthorizationScreensViewModel
import com.example.first_week_creating_ui_kit.viewModels.CommunityDetailsViewModel
import com.example.first_week_creating_ui_kit.viewModels.MoreScreenViewModel
import com.example.first_week_creating_ui_kit.viewModels.MyMeetingScreenDetailsViewModel
import com.example.firstweek_lessonfirst.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun BottomNavigationBar(navController: NavController, screens: List<BottomNavMenuItem>) {
    BottomNavigation(
        backgroundColor = AppTheme.colors.neutralColorBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Log.d("Navigation", "Current route: ${currentDestination?.route}")

        screens.forEach { screen ->
            val selected = screen.routes.any { route ->
                isRouteSelected(currentDestination?.route, route)
            }

            BottomNavigationItem(
                selected = selected,
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
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(bottom = AppTheme.dimens.paddingXSmall)
                        )
                    }
                },
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
                                    .padding(AppTheme.dimens.paddingXSmall)
                            )
                        }
                    }
                },
                alwaysShowLabel = false,
                selectedContentColor = AppTheme.colors.neutralColorFont,
                unselectedContentColor = AppTheme.colors.disabledColorForTab
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
        composable(Routes.AuthorizationScreen.SCREEN_AUTH_ROUTE) {
            bottomBarState.isVisible.value = false
            AuthScreen()

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
            val viewModel: AllMeetingDetailsViewModel = koinViewModel()
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
            val viewModel: CommunityDetailsViewModel = koinViewModel()
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
            val viewModel: MyMeetingScreenDetailsViewModel = koinViewModel()
            viewModel.initializeMyId(meetingId)
            MyMeetingScreenDetails(viewModel, navController)
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
