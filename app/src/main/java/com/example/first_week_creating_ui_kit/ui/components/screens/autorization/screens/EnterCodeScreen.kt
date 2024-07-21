package com.example.first_week_creating_ui_kit.ui.components.screens.autorization.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.data.data.ProfileRepoImpl
import com.example.first_week_creating_ui_kit.ui.components.atoms.ButtonType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomButton
import com.example.first_week_creating_ui_kit.ui.components.screens.autorization.base.Password
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.formatPhoneNumber
import com.example.first_week_creating_ui_kit.viewModels.AuthScreens
import com.example.first_week_creating_ui_kit.viewModels.AuthorizationScreensViewModel
import com.example.firstweek_lessonfirst.R

private const val PADDING_TOP_FOR_TEXT = 170
private const val PADDING_TOP_FOR_BUTTON = 70

@Composable
fun EnterCodeScreen(viewModel: AuthorizationScreensViewModel) {
    val password = remember {
        mutableStateOf("")
    }
    val phoneNumber = viewModel.fullPhoneNumber.value
    val formattedPhoneNumber = formatPhoneNumber(phoneNumber)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = PADDING_TOP_FOR_TEXT.dp)
    ) {
        Text(
            text = stringResource(id = R.string.enter_code),
            style = AppTheme.typo.h2,
            color = AppTheme.colors.neutralColorFont,
            modifier = Modifier.padding(bottom = AppTheme.dimens.paddingMedium)
        )
        Text(
            text = stringResource(id = R.string.we_sent_code_for_number),
            style = AppTheme.typo.bodyText2,
            color = AppTheme.colors.neutralColorFont,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = AppTheme.dimens.paddingMedium)

        )
        Text(
            text = formattedPhoneNumber,
            style = AppTheme.typo.bodyText2,
            color = AppTheme.colors.neutralColorFont,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = PADDING_TOP_FOR_BUTTON.dp)

        )
        Password(
            value = password.value,
            onValueChange = { password.value = it }
        )
        CustomButton(
            type = ButtonType.Ghost,
            text = stringResource(id = R.string.sent_code_again),
            isEnabled = password.value.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PADDING_TOP_FOR_BUTTON.dp),
            onClick = {
                viewModel.nextScreen(AuthScreens.EnterProfileDataScreen)
            }
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowCodeScreen() {
    EnterCodeScreen(viewModel = AuthorizationScreensViewModel(ProfileRepoImpl()))
}