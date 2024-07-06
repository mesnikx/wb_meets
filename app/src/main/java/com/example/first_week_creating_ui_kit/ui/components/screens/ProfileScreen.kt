package com.example.first_week_creating_ui_kit.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.components.atoms.AvatarType
import com.example.first_week_creating_ui_kit.ui.components.atoms.ButtonType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomAvatar
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomButton
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomTopBar
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.ProfileData
import com.example.firstweek_lessonfirst.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val myUser = ProfileData(
        name = "Иван",
        surname = "Иванов",
        phoneNumber = "+7 999 999-99-99",
        imageProfile = null
    )
    Scaffold(
        topBar = {
            CustomTopBar(
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
                        bottom = 52.dp + innerPadding.calculateBottomPadding(),
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
                    imageUri = myUser.imageProfile,
                    size = 200.dp,
                    backgroundColor = AppTheme.colors.neutralColorDivider,
                    modifier = Modifier
                        .padding(top = 46.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "${myUser.name} ${myUser.surname}",
                    style = AppTheme.typo.usernameText,
                    color = AppTheme.colors.neutralColorFont,
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingXXLarge)
                )
                Text(
                    text = myUser.phoneNumber,
                    style = AppTheme.typo.usernamePhoneNumber,
                    color = AppTheme.colors.neutralColorSecondaryText,
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall)
                )
                Row(
                    modifier = Modifier.padding(end = AppTheme.dimens.paddingLarge)
                ) {
                    CustomButton(
                        type = ButtonType.Secondary,
                        ifHaveIcon = true,
                        icon = R.drawable.ic_btn_twitter
                    )
                    CustomButton(
                        type = ButtonType.Secondary,
                        ifHaveIcon = true,
                        icon = R.drawable.ic_btn_instagram
                    )
                    CustomButton(
                        type = ButtonType.Secondary,
                        ifHaveIcon = true,
                        icon = R.drawable.ic_btn_linkedin
                    )
                    CustomButton(
                        type = ButtonType.Secondary,
                        ifHaveIcon = true,
                        icon = R.drawable.ic_btn_facebook
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowProfileScreen() {
    ProfileScreen()
}