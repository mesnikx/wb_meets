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
import androidx.compose.ui.unit.dp
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
                RoundedCornerShape(40.dp)
            )
            .padding(
                horizontal = AppTheme.dimens.padding8dp,
                vertical = AppTheme.dimens.padding2dp
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
        CustomChip(text = "Python", modifier = Modifier.padding(AppTheme.dimens.padding4dp))
        CustomChip(text = "Moscow", modifier = Modifier.padding(AppTheme.dimens.padding4dp))
        CustomChip(text = "Junior", modifier = Modifier.padding(AppTheme.dimens.padding4dp))
    }


}