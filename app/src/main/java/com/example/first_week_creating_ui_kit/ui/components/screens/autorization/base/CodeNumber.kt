package com.example.first_week_creating_ui_kit.ui.components.screens.autorization.base


import NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.Country

const val CODE_LENGTH = 4
const val DOT_SIZE = 24
const val DOT_SPACE = 40
val customTextSelectionColors = TextSelectionColors(
    handleColor = Transparent,
    backgroundColor = Transparent,
)

@Composable
fun Password(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(IntrinsicSize.Min)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DOT_SPACE.dp)
        ) {
            value.forEach {
                Text(
                    text = it.toString(),
                    color = AppTheme.colors.neutralColorFont,
                    style = AppTheme.typo.h1,
                )
            }
            repeat(CODE_LENGTH - value.length) {
                Box(
                    modifier = Modifier
                        .size(DOT_SIZE.dp)
                        .clip(CircleShape)
                        .background(AppTheme.colors.neutralColorDivider)
                )
            }
        }
        CompositionLocalProvider(
            LocalTextSelectionColors provides customTextSelectionColors,
        ) {
            BasicTextField(
                value = value,
                onValueChange = {
                    if (it.length <= CODE_LENGTH) onValueChange(it)
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    color = Transparent,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
                cursorBrush = SolidColor(Transparent),
                singleLine = true
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PasswordPreview() {
    val password = remember {
        mutableStateOf("")
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }
    val selectedCountry = remember { mutableStateOf(Country.countries[0]) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumberPicker(
            phoneNumber = phoneNumber.value,
            selectedCountry = selectedCountry.value,
            onPhoneNumberChanged = { phoneNumber.value = it },
            onFullPhoneNumberChanged = { phoneNumber.value = it }
        ) {

        }
        Password(
            value = password.value,
            onValueChange = { password.value = it }
        )


    }
}
