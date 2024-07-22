package com.example.first_week_creating_ui_kit.ui.components.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.data.bottomNavBarPadding
import com.example.first_week_creating_ui_kit.navigation.utils.LocalNavigator
import com.example.first_week_creating_ui_kit.ui.components.atoms.AvatarType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomAvatar
import com.example.first_week_creating_ui_kit.ui.components.atoms.NavigableTopBar
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.formatPhoneNumber
import com.example.first_week_creating_ui_kit.viewModels.MoreScreenViewModel
import com.example.firstweek_lessonfirst.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoreScreen(viewModel: MoreScreenViewModel = koinViewModel()) {
    val navigator = LocalNavigator.current
    val profileData = viewModel.profileData.value
    Scaffold(
        topBar = {
            NavigableTopBar(titleText = R.string.bot_nav_more)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = bottomNavBarPadding.dp + innerPadding.calculateBottomPadding(),
                        start = AppTheme.dimens.paddingXLarge + innerPadding.calculateStartPadding(
                            LayoutDirection.Ltr
                        ),
                        end = AppTheme.dimens.paddingXLarge + innerPadding.calculateEndPadding(
                            LayoutDirection.Ltr
                        )
                    )
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RectangleShape,
                    onClick = {
                        navigator.navigateToProfileScreen()
                    },
                    modifier = Modifier
                        .padding(
                            bottom = AppTheme.dimens.paddingLarge,
                            start = AppTheme.dimens.paddingSmall,
                            top = AppTheme.dimens.paddingSmall
                        )
                        .fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CustomAvatar(
                            type = AvatarType.AvatarProfile,
                            imageUri = profileData.imageProfile,
                            size = 50.dp,
                            backgroundColor = AppTheme.colors.neutralColorDivider,
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${profileData.name} ${profileData.surname}",
                                style = AppTheme.typo.bodyText1,
                                color = AppTheme.colors.neutralColorFont,
                                modifier = Modifier.padding(start = AppTheme.dimens.paddingXXLarge)
                            )
                            Text(
                                text = formatPhoneNumber(profileData.phoneNumber),
                                style = AppTheme.typo.metadata1,
                                color = AppTheme.colors.neutralColorSecondaryText,
                                modifier = Modifier.padding(start = AppTheme.dimens.paddingXXLarge)
                            )

                        }

                        Icon(
                            painter = painterResource(R.drawable.ic_go_next),
                            contentDescription = null,
                            tint = AppTheme.colors.neutralColorFont,
                        )
                    }

                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RectangleShape,
                    onClick = {
                        navigator.navigateToMyMeetingsScreen()
                    },
                    modifier = Modifier
                        .padding(
                            bottom = AppTheme.dimens.paddingLarge,
                            start = AppTheme.dimens.paddingSmall,
                            top = AppTheme.dimens.paddingSmall
                        )
                        .fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_nav_cup),
                            contentDescription = null
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = stringResource(id = R.string.my_meetings),
                                style = AppTheme.typo.bodyText1,
                                color = AppTheme.colors.neutralColorFont,
                                modifier = Modifier.padding(start = AppTheme.dimens.paddingMedium)
                            )
                        }
                        Icon(
                            painter = painterResource(R.drawable.ic_go_next),
                            contentDescription = null,
                            tint = AppTheme.colors.neutralColorFont,
                        )
                    }
                }
            }
        }
    )

}
