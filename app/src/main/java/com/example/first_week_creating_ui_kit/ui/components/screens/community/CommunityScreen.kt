package com.example.first_week_creating_ui_kit.ui.components.screens.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.bottomNavBarPadding
import com.example.first_week_creating_ui_kit.navigation.Routes
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomSearchBar
import com.example.first_week_creating_ui_kit.ui.components.atoms.NavigableTopBar
import com.example.first_week_creating_ui_kit.ui.components.molecules.ShowCardCommunity
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.viewModels.CommunityDetailsViewModel
import com.example.firstweek_lessonfirst.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommunityScreen(
    navController: NavController,
    viewModel: CommunityDetailsViewModel = koinViewModel()
) {
    val allCommunities = viewModel.allCommunityList.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            NavigableTopBar(titleText = R.string.bot_nav_community)
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
                    )
            ) {
                CustomSearchBar(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.padding(AppTheme.dimens.paddingLarge))
                ShowCardCommunity(
                    communities = allCommunities.value,
                    onCommunityClick = { communityId ->
                        navController.navigate("${Routes.Community.SCREEN_DETAIL_ROUTE}/$communityId")
                    }
                )
            }
        }
    )
}