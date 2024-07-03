package com.example.first_week_creating_ui_kit.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.navigation.utils.LocalNavigator
import com.example.first_week_creating_ui_kit.ui.components.atoms.AvatarType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomAvatar
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.ProfileData
import com.example.firstweek_lessonfirst.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen() {
    val navigator = LocalNavigator.current
    val myUser = ProfileData(
        name = "Иван",
        surname = "Иванов",
        phoneNumber = "+7 999 999-99-99",
        imageProfile = null
    )
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(
                    horizontal = AppTheme.dimens.padding24dp,
                    vertical = AppTheme.dimens.padding12dp
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.bot_nav_more),
                        style = AppTheme.typo.subtitle1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.Start)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colors.neutralColorForTopBar,
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = 160.dp + innerPadding.calculateBottomPadding(),
                        start = AppTheme.dimens.padding16dp + innerPadding.calculateStartPadding(
                            LayoutDirection.Ltr
                        ),
                        end = AppTheme.dimens.padding16dp + innerPadding.calculateEndPadding(
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
                            bottom = AppTheme.dimens.padding12dp,
                            start = AppTheme.dimens.padding4dp,
                            top = AppTheme.dimens.padding4dp
                        )
                        .fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CustomAvatar(
                            type = AvatarType.AvatarProfile,
                            imageUri = myUser.imageProfile,
                            size = 50.dp,
                            backgroundColor = AppTheme.colors.neutralColorDivider,
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${myUser.name} ${myUser.surname}",
                                style = AppTheme.typo.bodyText1,
                                color = AppTheme.colors.neutralColorFont,
                                modifier = Modifier.padding(start = AppTheme.dimens.padding20dp)
                            )
                            Text(
                                text = myUser.phoneNumber,
                                style = AppTheme.typo.metadata1,
                                color = AppTheme.colors.neutralColorSecondaryText,
                                modifier = Modifier.padding(start = AppTheme.dimens.padding20dp)
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
                            bottom = AppTheme.dimens.padding12dp,
                            start = AppTheme.dimens.padding4dp,
                            top = AppTheme.dimens.padding4dp
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
                                modifier = Modifier.padding(start = AppTheme.dimens.padding8dp)
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowMoreScreen() {
    MoreScreen()
}