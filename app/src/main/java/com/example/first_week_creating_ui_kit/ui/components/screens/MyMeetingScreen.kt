package com.example.first_week_creating_ui_kit.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomTopBar
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowCardMeeting
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.Meeting
import com.example.firstweek_lessonfirst.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMeetingScreen() {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { AllMeetingScreens.entries.size })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }

    val allMeetings = listOf(
        Meeting(
            title = "Developers meeting",
            dateAndPlace = "27.06.2024 - Moscow",
            isOver = true
        ),
        Meeting(
            title = "Kotlin Con",
            dateAndPlace = "29.08.2024"
        ),
        Meeting(
            title = "Mobius Fall",
            dateAndPlace = "20.11.2024",
            imageUrl = "https://sun9-57.userapi.com/impg/Umm90jen_qIn5iswC26Eg5B_WK3A1FhY5j3npA/8YSlbgn5oIo.jpg?size=600x600&quality=95&sign=7d019a27e80fa0c004065b3bcde32cea&type=album"
        ),
        Meeting(
            title = "Mobius Fall",
            dateAndPlace = "20.11.2024",
            chips = listOf("C++", "Kazan"),
            imageUrl = "https://sun9-57.userapi.com/impg/Umm90jen_qIn5iswC26Eg5B_WK3A1FhY5j3npA/8YSlbgn5oIo.jpg?size=600x600&quality=95&sign=7d019a27e80fa0c004065b3bcde32cea&type=album"
        ),
        Meeting(
            title = "Kotlin Copenhagen Conf",
            dateAndPlace = "25.04.2003 - Copenhagen",
            isOver = true,
            imageUrl = "https://kotlinconf.com/static/04144ed7b4514acc601a3cd340807378/37a55/global-desktop.png"
        ),
        Meeting(
            title = "DroidCon Berlin",
            dateAndPlace = "4-6.07.2024 - Berlin",
            imageUrl = "https://yt3.googleusercontent.com/ytc/AIdro_ksXZpIZNSuDbS_jm08lJvkxJzGhZJreQpryI3G-xNdMbU=s900-c-k-c0x00ffffff-no-rj"
        )
    )

    Scaffold(
        topBar = {
            CustomTopBar(
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
                        bottom = 52.dp + innerPadding.calculateBottomPadding(),
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
                                modifier = Modifier.padding(vertical = 12.dp)
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
                            ShowCardMeeting(meetings = allMeetings)
                        }

                        MyMeetingScreens.GoneMeetings.ordinal -> {
                            ShowCardMeeting(meetings = allMeetings.filter { !it.isOver })
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowMyMeetingScreen() {
    MyMeetingScreen()
}