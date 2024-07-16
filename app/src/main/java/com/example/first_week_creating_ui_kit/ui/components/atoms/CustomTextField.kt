package com.example.first_week_creating_ui_kit.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    height: Dp = 36.dp,
    hint: String,
    onSearchClicked: () -> Unit = {},
    onTextChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .shadow(
                elevation = AppTheme.dimens.paddingSmall,
                shape = RoundedCornerShape(AppTheme.dimens.paddingSmall)
            )
            .background(
                color = AppTheme.colors.neutralColorBackground,
                shape = RoundedCornerShape(AppTheme.dimens.paddingSmall)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onTextChange(it.text)
            },
            enabled = true,
            textStyle = AppTheme.typo.bodyText1,
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = AppTheme.colors.neutralColorDisabled,
                        style = AppTheme.typo.bodyText1,
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
            singleLine = true
        )
    }
}