package com.example.first_week_creating_ui_kit.ui.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.first_week_creating_ui_kit.navigation.utils.Navigator
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigableTopBar(
    titleText: Any,
    modifier: Modifier = Modifier,
    navIcon: Int? = null,
    actionIcon: Int? = null,
    navController: NavController? = null,
    isMapOpen: Boolean = false,
    onMapClose: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            when (titleText) {
                is Int -> {
                    Text(
                        text = stringResource(id = titleText),
                        modifier = Modifier.wrapContentSize(),
                        style = AppTheme.typo.subtitle1
                    )
                }

                is String -> {
                    Text(
                        text = titleText,
                        modifier = Modifier.wrapContentSize(),
                        style = AppTheme.typo.subtitle1
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colors.neutralColorForTopBar,
        ),
        navigationIcon = {
            if (navIcon != null && navController != null)
                IconButton(
                    onClick = {
                        if (isMapOpen) {
                            onMapClose()
                        } else {
                            Navigator(navController).back()
                        }
                    },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Icon(
                        painter = painterResource(navIcon),
                        contentDescription = null,
                        tint = AppTheme.colors.neutralColorFont
                    )
                }
        },
        actions = {
            if (actionIcon != null)
                Image(
                    painter = painterResource(actionIcon),
                    contentDescription = null,
                    modifier = Modifier.wrapContentSize()
                )
        }
    )
}