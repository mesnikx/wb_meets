package com.example.first_week_creating_ui_kit.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme

@Composable
fun CustomChip(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                AppTheme.colors.brandColorBackground,
                RoundedCornerShape(AppTheme.dimens.paddingXXLarge * 2)
            )
            .padding(
                horizontal = AppTheme.dimens.paddingMedium,
                vertical = AppTheme.dimens.paddingXSmall
            )
    ) {
        Text(
            text = text,
            style = AppTheme.typo.metadata3,
            color = AppTheme.colors.brandColorDarkOnPressed
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ShowCustomChips() {
    Row {
        CustomChip(text = "Python", modifier = Modifier.padding(AppTheme.dimens.paddingSmall))
        CustomChip(text = "Moscow", modifier = Modifier.padding(AppTheme.dimens.paddingSmall))
        CustomChip(text = "Junior", modifier = Modifier.padding(AppTheme.dimens.paddingSmall))
    }


}