package com.example.first_week_creating_ui_kit.ui.components.screens.community

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.domain.entities.CommunityData
import com.example.first_week_creating_ui_kit.ui.components.atoms.NavigableTopBar
import com.example.first_week_creating_ui_kit.ui.components.molecules.CardMeeting
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.ExpandableText
import com.example.first_week_creating_ui_kit.viewModels.CommunityDetailsViewModel
import com.example.firstweek_lessonfirst.R
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityScreenDetails(
    viewModel: CommunityDetailsViewModel = koinViewModel()
) {
    val community by viewModel.communityData.collectAsStateWithLifecycle()
    val meeting by viewModel.meetings.collectAsStateWithLifecycle()

    if (community.communityId != CommunityData.getDefault().communityId) {
        Scaffold(
            topBar = {
                NavigableTopBar(titleText = community.title)
            },
            containerColor = AppTheme.colors.neutralColorForTopBar,
            content = { innerPadding ->
                Box(Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = innerPadding.calculateTopPadding(),
                                bottom = com.example.data.bottomNavBarPadding.dp + innerPadding.calculateBottomPadding(),
                                start = AppTheme.dimens.paddingXXXLarge + innerPadding.calculateStartPadding(
                                    LayoutDirection.Ltr
                                ),
                                end = AppTheme.dimens.paddingXXXLarge + innerPadding.calculateEndPadding(
                                    LayoutDirection.Ltr
                                )
                            )
                    ) {
                        item {
                            ExpandableText(
                                text = community.description ?: "",
                                style = AppTheme.typo.metadata1,
                                color = AppTheme.colors.neutralColorSecondaryText,
                                collapsedMaxLine = COLLAPSED_LINE
                            )
                        }
                        item {
                            Text(
                                text = stringResource(id = R.string.meeting_of_community),
                                style = AppTheme.typo.bodyText1,
                                color = AppTheme.colors.neutralColorSecondaryText,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(
                                        top = AppTheme.dimens.paddingXXXLarge,
                                        bottom = AppTheme.dimens.paddingXLarge
                                    )
                            )
                        }
                        items(meeting) { meeting ->
                            CardMeeting(
                                title = meeting.title,
                                onCLick = { },
                                dateAndPlace = meeting.dateAndPlace,
                                isOver = meeting.isOver,
                                imageUrl = meeting.imageUrl,
                                chips = meeting.chips,
                                modifier = Modifier.padding((AppTheme.dimens.paddingSmall))
                            )
                        }
                    }
                }
            }
        )
    } else {
        Text(
            text = stringResource(
                id = R.string.community_not_found
            ),
            style = AppTheme.typo.h1
        )
    }
}

private const val COLLAPSED_LINE = 14

