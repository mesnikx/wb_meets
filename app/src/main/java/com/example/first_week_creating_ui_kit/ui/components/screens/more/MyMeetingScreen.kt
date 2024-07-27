package com.example.first_week_creating_ui_kit.ui.components.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.bottomNavBarPadding
import com.example.first_week_creating_ui_kit.navigation.Routes
import com.example.first_week_creating_ui_kit.ui.components.atoms.NavigableTopBar
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowCardMeeting
import com.example.first_week_creating_ui_kit.ui.components.screens.allMeeting.AllMeetingScreens
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.viewModels.MyMeetingScreenDetailsViewModel
import com.example.firstweek_lessonfirst.R
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyMeetingScreen(
    navController: NavController,
    viewModel: MyMeetingScreenDetailsViewModel = koinViewModel()
) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { AllMeetingScreens.entries.size })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }

    val myMeetings by viewModel.myMeetings.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            NavigableTopBar(
                titleText = R.string.my_meetings,
                navIcon = R.drawable.ic_nav_back
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = bottomNavBarPadding.dp + innerPadding.calculateBottomPadding(),
                        start = AppTheme.dimens.paddingXXXLarge + innerPadding.calculateStartPadding(
                            LayoutDirection.Ltr
                        ),
                        end = AppTheme.dimens.paddingXXXLarge + innerPadding.calculateEndPadding(
                            LayoutDirection.Ltr
                        )
                    )
            ) {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = AppTheme.dimens.paddingXLarge
                        ),
                    containerColor = Color.Transparent,
                    divider = {},
                    indicator = { tabPositions ->
                        if (selectedTabIndex < tabPositions.size)
                            TabRowDefaults.SecondaryIndicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                height = 2.dp,
                                color = AppTheme.colors.brandColorDefault
                            )
                    },
                ) {
                    MyMeetingScreens.entries.forEachIndexed { index, currentTab ->
                        Tab(
                            selected = selectedTabIndex == index,
                            selectedContentColor = AppTheme.colors.brandColorDefault,
                            unselectedContentColor = AppTheme.colors.disabledColorForTab,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(currentTab.ordinal)
                                }
                            }) {
                            Text(
                                text = if (selectedTabIndex == index)
                                    stringResource(id = currentTab.selectedText).uppercase()
                                else stringResource(id = currentTab.unselectedText).uppercase(),
                                style = AppTheme.typo.textForTabs,
                                modifier = Modifier.padding(vertical = AppTheme.dimens.paddingLarge)
                            )
                        }
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) { page ->
                    when (page) {
                        MyMeetingScreens.PlannedMeetings.ordinal -> {
                            ShowCardMeeting(
                                meetingData = myMeetings.filter { !it.isOver },
                                onMeetingClick = { meetingId ->
                                    navController.navigate("${Routes.More.SCREEN_DETAIL_ROUTE}/$meetingId")
                                }
                            )
                        }

                        MyMeetingScreens.GoneMeetings.ordinal -> {
                            ShowCardMeeting(
                                meetingData = myMeetings.filter { it.isOver },
                                onMeetingClick = { meetingId ->
                                    navController.navigate("${Routes.More.SCREEN_DETAIL_ROUTE}/$meetingId")
                                }
                            )
                        }
                    }
                }
            }

        }
    )
}


enum class MyMeetingScreens(
    val selectedText: Int,
    val unselectedText: Int
) {
    PlannedMeetings(
        selectedText = R.string.meeting_planned,
        unselectedText = R.string.meeting_planned
    ),
    GoneMeetings(
        selectedText = R.string.meeting_gone,
        unselectedText = R.string.meeting_gone
    )
}