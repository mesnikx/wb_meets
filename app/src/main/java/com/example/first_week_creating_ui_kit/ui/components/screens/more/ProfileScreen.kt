package com.example.first_week_creating_ui_kit.ui.components.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.components.atoms.AvatarType
import com.example.first_week_creating_ui_kit.ui.components.atoms.ButtonType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomAvatar
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomButton
import com.example.first_week_creating_ui_kit.ui.components.atoms.NavigableTopBar
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.data.bottomNavBarPadding
import com.example.first_week_creating_ui_kit.ui.utils.formatPhoneNumber
import com.example.first_week_creating_ui_kit.viewModels.MoreScreenViewModel
import com.example.firstweek_lessonfirst.R

@Composable
fun ProfileScreen(koinViewModel: MoreScreenViewModel) {
    val profileData = koinViewModel.profileData.value
    Scaffold(
        topBar = {
            NavigableTopBar(
                titleText = R.string.bot_nav_meetings,
                navIcon = R.drawable.ic_nav_back,
                actionIcon = R.drawable.ic_nav_edit
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
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomAvatar(
                    type = AvatarType.AvatarProfile,
                    imageUri = null,
                    size = 200.dp,
                    backgroundColor = AppTheme.colors.neutralColorDivider,
                    modifier = Modifier
                        .padding(top = AppTheme.dimens.paddingXXXLarge * 2)
                        .align(Alignment.CenterHorizontally)
                )
                profileData.let { profile ->
                    Text(
                        text = "${profile.name} ${profile.surname}",
                        style = AppTheme.typo.usernameText,
                        color = AppTheme.colors.neutralColorFont,
                        modifier = Modifier.padding(top = AppTheme.dimens.paddingXXLarge)
                    )
                    Text(
                        text = formatPhoneNumber(profile.phoneNumber),
                        style = AppTheme.typo.usernamePhoneNumber,
                        color = AppTheme.colors.neutralColorSecondaryText,
                        modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall)
                    )
                }
                Row(
                    modifier = Modifier.padding(end = AppTheme.dimens.paddingLarge)
                ) {
                    CustomButton(
                        type = ButtonType.Secondary,
                        ifHaveIcon = true,
                        icon = R.drawable.ic_btn_twitter,
                        modifier = Modifier.padding(horizontal = AppTheme.dimens.paddingMedium),
                    )
                    CustomButton(
                        type = ButtonType.Secondary,
                        ifHaveIcon = true,
                        icon = R.drawable.ic_btn_instagram,
                        modifier = Modifier.padding(horizontal = AppTheme.dimens.paddingMedium),
                    )
                    CustomButton(
                        type = ButtonType.Secondary,
                        ifHaveIcon = true,
                        icon = R.drawable.ic_btn_linkedin,
                        modifier = Modifier.padding(horizontal = AppTheme.dimens.paddingMedium),
                    )
                    CustomButton(
                        type = ButtonType.Secondary,
                        ifHaveIcon = true,
                        icon = R.drawable.ic_btn_facebook,
                        modifier = Modifier.padding(horizontal = AppTheme.dimens.paddingMedium),
                    )
                }
            }
        }
    )
}
