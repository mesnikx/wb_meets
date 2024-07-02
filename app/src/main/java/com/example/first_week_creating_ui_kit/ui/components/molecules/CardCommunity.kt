package com.example.first_week_creating_ui_kit.ui.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.components.atoms.AvatarType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomAvatar
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.first_week_creating_ui_kit.ui.utils.toFormattedString
import com.example.firstweek_lessonfirst.R

@Composable
fun CardCommunity(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String? = null,
    numberOfSubs: Int,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RectangleShape,
        onClick = onClick,
        modifier = modifier
            .padding(
                bottom = AppTheme.dimens.padding12dp,
                start = AppTheme.dimens.padding4dp,
                top = AppTheme.dimens.padding4dp
            )
            .fillMaxWidth()
    ) {
        Row {
            CustomAvatar(
                type = AvatarType.AvatarMeeting,
                imageUri = imageUrl,
                size = 48.dp,
                modifier = Modifier.padding(
                    bottom = AppTheme.dimens.padding12dp,
                    end = AppTheme.dimens.padding12dp
                )
            )
            Column {
                Text(
                    text = title,
                    style = AppTheme.typo.bodyText1,
                    color = AppTheme.colors.neutralColorFont,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${numberOfSubs.toFormattedString()} ${stringResource(id = R.string.numberOfSubs)}",
                    style = AppTheme.typo.metadata1,
                    color = AppTheme.colors.neutralColorSecondaryText,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = AppTheme.dimens.padding2dp / 2,
            color = AppTheme.colors.neutralColorDivider
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowCardCommunity() {
    CardCommunity(title = "Design", numberOfSubs = 10000)
}