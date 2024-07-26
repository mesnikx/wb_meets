package com.example.first_week_creating_ui_kit.ui.components.screens.autorization.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.data.bottomNavBarPadding
import com.example.first_week_creating_ui_kit.navigation.utils.LocalNavigator
import com.example.first_week_creating_ui_kit.ui.components.atoms.AvatarType
import com.example.first_week_creating_ui_kit.ui.components.atoms.ButtonType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomAvatar
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomButton
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomTextField
import com.example.first_week_creating_ui_kit.ui.components.atoms.NavigableTopBar
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.viewModels.AuthorizationScreensViewModel
import com.example.firstweek_lessonfirst.R
import org.koin.androidx.compose.koinViewModel

private const val PADDING_TOP_FOR_BUTTON = 56

@SuppressLint("UnrememberedMutableState")
@Composable
fun EnterProfileData(viewModel: AuthorizationScreensViewModel = koinViewModel()) {
    val navigator = LocalNavigator.current
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    val isButtonEnabled by derivedStateOf {
        name.isNotBlank()
    }
    Scaffold(
        topBar = {
            NavigableTopBar(
                titleText = stringResource(id = R.string.profile),
                navIcon = R.drawable.ic_nav_back,
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
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CustomAvatar(
                    type = AvatarType.AvatarProfile,
                    isEditable = true,
                    modifier = Modifier.padding(top = PADDING_TOP_FOR_BUTTON.dp)
                )
                CustomTextField(
                    hint = stringResource(id = R.string.enter_name),
                    onTextChange = { newName ->
                        name = newName
                        viewModel.updateProfile(
                            name,
                            surname
                        )
                    },
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingXXXLarge)
                )
                CustomTextField(
                    onTextChange = { newSurname ->
                        surname = newSurname
                        viewModel.updateProfile(
                            name,
                            surname
                        )
                    },
                    hint = stringResource(id = R.string.enter_surname),
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge)
                )
                CustomButton(
                    type = ButtonType.Primary,
                    text = stringResource(id = R.string.save_button),
                    onClick = {
                        viewModel.updateProfile(
                            name,
                            surname
                        )
                        viewModel.saveProfileData()
                        navigator.navigateAllMeetingScreen()
                    },
                    isEnabled = isButtonEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = PADDING_TOP_FOR_BUTTON.dp)
                )
            }
        }
    )

}