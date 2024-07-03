package com.example.first_week_creating_ui_kit.navigation

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.first_week_creating_ui_kit.navigation.utils.BottomBarState
import com.example.first_week_creating_ui_kit.navigation.utils.LocalBottomBarState
import com.example.first_week_creating_ui_kit.navigation.utils.LocalNavigator
import com.example.first_week_creating_ui_kit.navigation.utils.LocalSnackbarHost
import com.example.first_week_creating_ui_kit.navigation.utils.Navigator
import com.example.first_week_creating_ui_kit.ui.components.screens.AllMeetingScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.CommunityScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.MoreScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.MyMeetingScreen
import com.example.first_week_creating_ui_kit.ui.components.screens.ProfileScreen
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.firstweek_lessonfirst.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootScreen() {
    val navController = rememberNavController()
    val screens = listOf(
        BottomBar.AllMeetings,
        BottomBar.Community,
        BottomBar.More
    )
    val navigator = Navigator(navController)
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val bottomBarState = remember {
        BottomBarState()
    }
    CompositionLocalProvider(
        LocalNavigator provides navigator,
        LocalSnackbarHost provides snackbarHostState,
        LocalBottomBarState provides bottomBarState
    ) {

        Scaffold(
            bottomBar = {
                if (bottomBarState.isVisible.value) {
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
                                                    .padding(top = AppTheme.dimens.padding4dp)
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
                                            modifier = Modifier.size(32.dp),
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }, snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }) {
            NavHost(
                navController = navController,
                startDestination = Routes.AllMeeting.SCREEN_ROUTE
            ) {
                composable(route = Routes.AllMeeting.SCREEN_ROUTE) {
                    AllMeetingScreen()
                }
                composable(route = Routes.Community.SCREEN_ROUTE) {
                    CommunityScreen()
                }
                composable(route = Routes.More.SCREEN_ROUTE_MORE) {
                    MoreScreen()
                }
                composable(route= Routes.More.SCREEN_ROUTE_PROFILE) {
                    ProfileScreen()
                }
                composable(route = Routes.More.SCREE_ROUTE_MY_MEETING) {
                    MyMeetingScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowRootScreen() {
    RootScreen()
}
