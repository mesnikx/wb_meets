package com.example.first_week_creating_ui_kit.ui.components.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.firstweek_lessonfirst.R

sealed interface ButtonType {
    data object Primary : ButtonType
    data object Secondary : ButtonType
    data object Ghost : ButtonType
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.button),
    onClick: () -> Unit = {},
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(30.dp),
    type: ButtonType = ButtonType.Primary,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    when (type) {
        ButtonType.Primary -> {
            Button(
                onClick = onClick,
                enabled = isEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressed) AppTheme.colors.brandColorDarkOnPressed
                    else AppTheme.colors.brandColorDefault,
                    contentColor = AppTheme.colors.neutralColorBackground,
                    disabledContainerColor = AppTheme.colors.brandColorDefault.copy(alpha = 0.5f),
                    disabledContentColor = AppTheme.colors.neutralColorSecondaryBackground
                ),
                shape = shape,
                modifier = modifier
                    .padding(AppTheme.dimens.padding8dp),
            ) {
                Text(
                    text,
                    style = AppTheme.typo.subtitle2,
                    textAlign = TextAlign.Center
                )
            }
        }

        ButtonType.Secondary -> {
            OutlinedButton(
                onClick = onClick,
                enabled = isEnabled,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (isPressed) AppTheme.colors.brandColorDarkOnPressed
                    else AppTheme.colors.brandColorDefault,
                    disabledContentColor = AppTheme.colors.brandColorDefault.copy(alpha = 0.5f),

                    ),
                border = BorderStroke(
                    2.dp,
                    if (!isEnabled) AppTheme.colors.brandColorDefault.copy(alpha = 0.5f)
                    else if (isPressed) AppTheme.colors.brandColorDarkOnPressed
                    else AppTheme.colors.brandColorDefault,
                ),
                shape = shape,
                interactionSource = interactionSource,
                modifier = modifier
                    .padding(AppTheme.dimens.padding8dp)
            ) {
                Text(
                    text,
                    style = AppTheme.typo.subtitle2,
                    textAlign = TextAlign.Center
                )
            }
        }

        ButtonType.Ghost -> {
            TextButton(
                onClick = onClick,
                enabled = isEnabled,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (isPressed) AppTheme.colors.brandColorDarkOnPressed
                    else AppTheme.colors.brandColorDefault,
                    disabledContentColor = AppTheme.colors.brandColorDefault.copy(alpha = 0.5f)
                ),
                shape = shape,
                interactionSource = interactionSource,
                modifier = modifier
                    .padding(AppTheme.dimens.padding8dp)

            ) {
                Text(
                    text,
                    style = AppTheme.typo.subtitle2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun MyApp() {
    Column(modifier = Modifier.padding(16.dp)) {
        CustomButton(
            type = ButtonType.Primary,
            isEnabled = true,
            modifier = Modifier
        )

        CustomButton(
            type = ButtonType.Secondary,
            isEnabled = true,
            modifier = Modifier
        )

        CustomButton(
            type = ButtonType.Ghost,
            isEnabled = true,
            modifier = Modifier
        )

        CustomButton(

            type = ButtonType.Primary,
            isEnabled = false,
            modifier = Modifier
        )
        CustomButton(
            type = ButtonType.Secondary,
            isEnabled = false,
            modifier = Modifier
        )
        CustomButton(
            type = ButtonType.Ghost,
            isEnabled = false,
            modifier = Modifier
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}