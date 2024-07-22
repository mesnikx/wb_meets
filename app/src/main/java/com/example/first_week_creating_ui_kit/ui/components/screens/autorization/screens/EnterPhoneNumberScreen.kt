package com.example.first_week_creating_ui_kit.ui.components.screens.autorization.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.components.atoms.ButtonType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomButton
import com.example.first_week_creating_ui_kit.ui.components.screens.autorization.base.NumberPicker
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.viewModels.AuthScreens
import com.example.first_week_creating_ui_kit.viewModels.AuthorizationScreensViewModel
import com.example.firstweek_lessonfirst.R
import org.koin.androidx.compose.koinViewModel

private const val PADDING_TOP_FOR_TEXT = 170
private const val PADDING_TOP_FOR_BUTTON = 70

@Composable
fun EnterPhoneNumberScreen(viewModel: AuthorizationScreensViewModel = koinViewModel()) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            top = PADDING_TOP_FOR_TEXT.dp,
            start = AppTheme.dimens.paddingXXXLarge,
            end = AppTheme.dimens.paddingXXXLarge,
        )
    ) {
        Text(
            text = stringResource(id = R.string.enter_phone_number),
            style = AppTheme.typo.h2,
            color = AppTheme.colors.neutralColorFont,
            modifier = Modifier.padding(bottom = AppTheme.dimens.paddingMedium)
        )
        Text(
            text = stringResource(id = R.string.we_will_sent_code),
            style = AppTheme.typo.bodyText2,
            color = AppTheme.colors.neutralColorFont,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = AppTheme.dimens.paddingXXXLarge * 2)

        )
        NumberPicker(
            phoneNumber = viewModel.phoneNumber.value,
            selectedCountry = viewModel.selectedCountry.value,
            onPhoneNumberChanged = { viewModel.updatePhoneNumber(it) },
            onFullPhoneNumberChanged = { viewModel.updatePhoneNumber(it) },
            onCountryChanged = { viewModel.updateCountryCode(it) }
        )
        CustomButton(
            type = ButtonType.Primary,
            text = stringResource(id = R.string.continue_button),
            onClick = {
                viewModel.nextScreen(AuthScreens.EnterCodeScreen)
            },
            isEnabled = viewModel.phoneNumber.value.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PADDING_TOP_FOR_BUTTON.dp)
        )
    }
}
