package com.example.first_week_creating_ui_kit.ui.components.screens.more

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.first_week_creating_ui_kit.ui.components.atoms.ButtonType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomButton
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomChip
import com.example.first_week_creating_ui_kit.ui.components.atoms.NavigableTopBar
import com.example.first_week_creating_ui_kit.ui.components.molecules.LineWithPeople
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.ExpandableText
import com.example.first_week_creating_ui_kit.viewModels.MyMeetingScreenDetailsViewModel
import com.example.firstweek_lessonfirst.R
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyMeetingScreenDetails(
    viewModel: MyMeetingScreenDetailsViewModel,
    navController: NavController
) {
    val card = viewModel.card.value
    val mapIsVisible = remember { mutableStateOf(false) }
    if (card.meetingId != com.example.domain.domain.entities.MeetingData.getDefault().meetingId) {
        Scaffold(
            topBar = {
                NavigableTopBar(
                    titleText = card.title,
                    navIcon = R.drawable.ic_nav_back,
                    actionIcon = if (card.isOver) R.drawable.ic_meeting_is_over else null,
                    navController = navController,
                    isMapOpen = mapIsVisible.value,
                    onMapClose = { mapIsVisible.value = false }
                )
            },
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
                            Column(
                                modifier = Modifier
                                    .padding(top = AppTheme.dimens.paddingXLarge)
                                    .wrapContentSize()
                            ) {
                                Text(
                                    text = card.dateAndPlace,
                                    style = AppTheme.typo.bodyText1,
                                    color = AppTheme.colors.neutralColorSecondaryText
                                )
                                Row {
                                    card.chips.forEach { chipText ->
                                        CustomChip(
                                            text = chipText,
                                            modifier = Modifier.padding(
                                                top = AppTheme.dimens.paddingSmall,
                                                end = AppTheme.dimens.paddingSmall
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = AppTheme.dimens.paddingLarge)
                                    .clip(RoundedCornerShape(AppTheme.dimens.paddingXXLarge))
                                    .clickable { mapIsVisible.value = true }
                            ) {
                                AsyncImage(
                                    model = card.mapUrl,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                        item {
                            ExpandableText(
                                text = card.description ?: "",
                                style = AppTheme.typo.metadata1,
                                color = AppTheme.colors.neutralColorSecondaryText,
                                collapsedMaxLine = COLLAPSED_LINE,
                            )
                        }
                        item {
                            LineWithPeople(
                                avatars = card.meetingGuests,
                                modifier = Modifier.padding(top = AppTheme.dimens.paddingXXLarge)
                            )
                        }
                        item {
                            when {
                                card.isOver -> CustomButton(
                                    type = ButtonType.Secondary,
                                    text = stringResource(id = R.string.i_will_come_next_time),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = AppTheme.dimens.paddingMedium)
                                )

                                else -> CustomButton(
                                    type = ButtonType.Primary,
                                    text = stringResource(id = R.string.i_will_come),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = AppTheme.dimens.paddingMedium)
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        visible = mapIsVisible.value,
                        enter = fadeIn(animationSpec = tween(ANIMATION_DURATION)) + expandIn(),
                        exit = fadeOut(animationSpec = tween(ANIMATION_DURATION)) + shrinkOut()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = BACKGROUND_ALPHA)),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = card.mapUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .zoomable(state = rememberZoomableState())
                            )
                        }
                    }
                }
            }
        )
    } else {
        Text(
            text = stringResource(id = R.string.meeting_not_found),
            style = AppTheme.typo.h1
        )
    }
}

private const val ANIMATION_DURATION = 300
private const val BACKGROUND_ALPHA = 0.8f
private const val COLLAPSED_LINE = 8