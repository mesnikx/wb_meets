package com.example.first_week_creating_ui_kit.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomSearchBar
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowCardCommunity
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.Community
import com.example.firstweek_lessonfirst.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen() {
    val allCommunities = listOf(
        Community(
            title = "Designa",
            numberOfSubs = 10000,
            imageUrl = "https://s3-alpha-sig.figma.com/img/139e/f96e/e0b474d2ee5737240e30042a2d50330c?Expires=1721001600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=fotEHTyJgnbLmYhIPEVwbs9pB9f~WJfhN-I4sJDto7L4ZKlnWnWe4v8Yngpm5VLPGvvLHrZeQQ~TNzYT3nb0mcyRcieJotxOaxoP7TtqVCnVL0xCTwdJA~V5GJOmCh9a8rIlgcvtU6rE9osn657cd-H8Yhxg1Y06FO8f6IxV45HcVeNmU-spdl6xLfLN6eM5FnUWMcXKtcWIH6emfv38kKV6IR8khaJ3A7tj3nFLe8vp5CjyFpW5otwi8ajiCvO7d0G1bH5xZzwOmoMMmskskR2s5~3Lpon2RteG2To5LuLaRnOc8jmQfXx0YQpFK1lvxBCASlCrdRWaEka0tY~VEQ__"
        ),
        Community(
            title = "52",
            numberOfSubs = 10000,
            imageUrl = "https://t3.ftcdn.net/jpg/04/34/65/92/360_F_434659284_VC3aewbswcKYxziva5bWwRbPu4KQ1aEN.jpg"
        ), Community(
            title = "52",
            numberOfSubs = 10000,
            imageUrl = "https://t3.ftcdn.net/jpg/04/34/65/92/360_F_434659284_VC3aewbswcKYxziva5bWwRbPu4KQ1aEN.jpg"
        ), Community(
            title = "52",
            numberOfSubs = 10000,
            imageUrl = "https://t3.ftcdn.net/jpg/04/34/65/92/360_F_434659284_VC3aewbswcKYxziva5bWwRbPu4KQ1aEN.jpg"
        ), Community(
            title = "52",
            numberOfSubs = 10000,
            imageUrl = "https://t3.ftcdn.net/jpg/04/34/65/92/360_F_434659284_VC3aewbswcKYxziva5bWwRbPu4KQ1aEN.jpg"
        ), Community(
            title = "52",
            numberOfSubs = 10000,
            imageUrl = "https://t3.ftcdn.net/jpg/04/34/65/92/360_F_434659284_VC3aewbswcKYxziva5bWwRbPu4KQ1aEN.jpg"
        ), Community(
            title = "52",
            numberOfSubs = 10000,
            imageUrl = "https://t3.ftcdn.net/jpg/04/34/65/92/360_F_434659284_VC3aewbswcKYxziva5bWwRbPu4KQ1aEN.jpg"
        ),
        Community(
            title = "52",
            numberOfSubs = 10000,
            imageUrl = "https://t3.ftcdn.net/jpg/04/34/65/92/360_F_434659284_VC3aewbswcKYxziva5bWwRbPu4KQ1aEN.jpg"
        )
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
                        text = stringResource(id = R.string.bot_nav_community),
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
                        bottom = 52.dp + innerPadding.calculateBottomPadding(),
                        start = AppTheme.dimens.padding24dp + innerPadding.calculateStartPadding(
                            LayoutDirection.Ltr
                        ),
                        end = AppTheme.dimens.padding24dp + innerPadding.calculateEndPadding(
                            LayoutDirection.Ltr
                        )
                    )
            ) {
                CustomSearchBar(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.padding(12.dp))
                ShowCardCommunity(communities = allCommunities)
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowCommunityScreen() {
    CommunityScreen()
}