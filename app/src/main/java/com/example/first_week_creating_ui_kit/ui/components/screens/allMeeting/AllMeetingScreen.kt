package com.example.first_week_creating_ui_kit.ui.components.screens.allMeeting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.data.bottomNavBarPadding
import com.example.domain.domain.entities.MeetingData
import com.example.first_week_creating_ui_kit.navigation.Routes
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomSearchBar
import com.example.first_week_creating_ui_kit.ui.components.atoms.NavigableTopBar
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowCardMeeting
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.viewModels.AllMeetingDetailsViewModel
import com.example.firstweek_lessonfirst.R
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllMeetingScreen(
    navController: NavController,
    viewModel: AllMeetingDetailsViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { AllMeetingScreens.entries.size })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }
    val allMeetings by viewModel.meetings.collectAsStateWithLifecycle(initialValue = emptyList())

    Scaffold(
        topBar = {
            NavigableTopBar(
                titleText = R.string.bot_nav_meetings,
                actionIcon = R.drawable.ic_nav_add
            )
        },
        containerColor = AppTheme.colors.neutralColorForTopBar,
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
                CustomSearchBar(modifier = Modifier.fillMaxWidth())
                MeetingTabs(
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = { index ->
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
                MeetingContentPager(
                    pagerState = pagerState,
                    allMeetings = allMeetings,
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun MeetingTabs(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = AppTheme.dimens.paddingXLarge,
                top = AppTheme.dimens.paddingXLarge
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
        AllMeetingScreens.entries.forEachIndexed { index, currentTab ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = AppTheme.colors.brandColorDefault,
                unselectedContentColor = AppTheme.colors.disabledColorForTab,
                onClick = { onTabSelected(index) }) {
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
}

@Composable
fun MeetingContentPager(
    pagerState: PagerState,
    allMeetings: List<MeetingData>,
    navController: NavController
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(ANIMATION_DURATION)),
            exit = fadeOut(animationSpec = tween(ANIMATION_DURATION))
        ) {
            when (page) {
                AllMeetingScreens.AllMeetings.ordinal -> {
                    ShowCardMeeting(
                        meetingData = allMeetings,
                        onMeetingClick = { meetingId ->
                            navController.navigate("${Routes.AllMeeting.SCREEN_DETAIL_ROUTE}/$meetingId")
                        }
                    )
                }

                AllMeetingScreens.Active.ordinal -> {
                    ShowCardMeeting(
                        meetingData = allMeetings.filter { !it.isOver },
                        onMeetingClick = { meetingId ->
                            navController.navigate("${Routes.AllMeeting.SCREEN_DETAIL_ROUTE}/$meetingId")
                        }
                    )
                }
            }
        }
    }
}

enum class AllMeetingScreens(
    val selectedText: Int,
    val unselectedText: Int
) {
    AllMeetings(
        selectedText = R.string.all_meetings,
        unselectedText = R.string.all_meetings
    ),
    Active(
        selectedText = R.string.active_meetings,
        unselectedText = R.string.active_meetings
    )
}
private const val ANIMATION_DURATION = 300
