package com.example.first_week_creating_ui_kit.ui.components.screens

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.navigation.RootScreen
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowCardMeeting
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.firstweek_lessonfirst.R
import kotlinx.coroutines.launch

@Composable
fun AllMeetingScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { AllMeetingScreens.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.bot_nav_meetings),
                        modifier = Modifier.wrapContentSize()
                    )
                },
                contentColor = AppTheme.colors.neutralColorFont,
                backgroundColor = AppTheme.colors.neutralColorSecondaryBackground,
                actions = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_nav_add),
                            contentDescription = null,
                            modifier = Modifier.wrapContentSize()
                        )
                }
            )

        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it.calculateTopPadding())
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Transparent

            ) {
                AllMeetingScreens.entries.forEachIndexed { index, currentTab ->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        selectedContentColor = AppTheme.colors.brandColorDefault,
                        unselectedContentColor = AppTheme.colors.disabledColorForTab,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        }) {
                        Text(
                            text = if (selectedTabIndex.value == index)
                                stringResource(id = currentTab.selectedText)
                            else stringResource(id = currentTab.unselectedText),
                            style = AppTheme.typo.textForTabs
                        )
                    }
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
            ) {
                ShowCardMeeting()
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
        unselectedText = R.string.active_meetings
    ),
    Active(
        selectedText = R.string.active_meetings,
        unselectedText = R.string.all_meetings
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowAllMeetingScreen() {
    RootScreen()
}
