package com.example.first_week_creating_ui_kit.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.firstweek_lessonfirst.R

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search),
    height: Dp = 36.dp,
    onSearchClicked: () -> Unit = {},
    onTextChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .shadow(
                elevation = AppTheme.dimens.padding4dp,
                shape = RoundedCornerShape(AppTheme.dimens.padding4dp)
            )
            .background(
                color = AppTheme.colors.neutralColorBackground,
                shape = RoundedCornerShape(AppTheme.dimens.padding4dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .weight(0.5f)
                .fillMaxSize()
                .background(color = Color.Transparent, shape = CircleShape)
                .clickable {
                    if (text.text.isNotEmpty()) {
                        text = TextFieldValue(text = "")
                        onTextChange("")
                    }
                },
        ) {
            if (text.text.isNotEmpty()) {
                Icon(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(AppTheme.dimens.padding8dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.search),
                    tint = AppTheme.colors.neutralColorFont
                )
            } else {
                Icon(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(AppTheme.dimens.padding8dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(R.string.search),
                    tint = AppTheme.colors.neutralColorDisabled
                )
            }
        }
        BasicTextField(
            modifier = modifier
                .weight(5f)
                .fillMaxWidth(),
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
                        style = AppTheme.typo.bodyText1
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

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    CustomSearchBar(modifier = Modifier)
}