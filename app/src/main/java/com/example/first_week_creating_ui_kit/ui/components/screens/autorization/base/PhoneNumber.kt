import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.first_week_creating_ui_kit.ui.components.screens.autorization.base.PhoneField
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.Country

const val PHONE_NUMBER_LENGTH = 10

@Composable
fun NumberPicker(
    phoneNumber: String,
    selectedCountry: Country,
    onPhoneNumberChanged: (String) -> Unit,
    onFullPhoneNumberChanged: (String) -> Unit,
    onCountryChanged: (Country) -> Unit,
) {
    val countries = Country.countries
    var isDropdownVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(AppTheme.dimens.paddingMedium))
                .clickable { isDropdownVisible = true }
                .background(AppTheme.colors.neutralColorSecondaryBackground)
                .padding(
                    horizontal = AppTheme.dimens.paddingMedium,
                    vertical = AppTheme.dimens.paddingXMedium
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.paddingMedium)
            ) {
                Image(
                    painter = painterResource(id = selectedCountry.countryVector),
                    contentDescription = null
                )
                Text(
                    text = selectedCountry.code,
                    style = AppTheme.typo.bodyText1,
                    color = if (phoneNumber.isEmpty()) AppTheme.colors.neutralColorSecondaryText else AppTheme.colors.neutralColorFont
                )
            }
        }

        DropdownMenu(
            expanded = isDropdownVisible,
            onDismissRequest = { isDropdownVisible = false },
            modifier = Modifier.padding(top = AppTheme.dimens.paddingMedium)
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.paddingMedium)
                        ) {
                            Text(country.code)
                            Text(country.name)
                        }
                    },
                    onClick = {
                        onCountryChanged(country)
                        isDropdownVisible = false
                        onFullPhoneNumberChanged(phoneNumber)
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = country.countryVector),
                            contentDescription = null
                        )
                    },
                )
            }
        }

        Spacer(modifier = Modifier.width(AppTheme.dimens.paddingXSmall))
        PhoneField(
            phone = phoneNumber,
            onPhoneChanged = {
                onPhoneNumberChanged(it.take(PHONE_NUMBER_LENGTH))
                onFullPhoneNumberChanged(it.take(PHONE_NUMBER_LENGTH))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = AppTheme.dimens.paddingXMedium,
                    horizontal = AppTheme.dimens.paddingMedium
                )
                .clip(RoundedCornerShape(AppTheme.dimens.paddingMedium))
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowNumberPicker() {
    val phoneNumber = remember { mutableStateOf("") }
    val selectedCountry = remember { mutableStateOf(Country.countries[0]) }
    NumberPicker(
        phoneNumber = phoneNumber.value,
        selectedCountry = selectedCountry.value,
        onPhoneNumberChanged = { phoneNumber.value = it },
        onFullPhoneNumberChanged = { phoneNumber.value = it },
        onCountryChanged = {
            selectedCountry.value = selectedCountry.value.copy(
                code = it.code,
                countryVector = it.countryVector
            )
        }
    )
}
