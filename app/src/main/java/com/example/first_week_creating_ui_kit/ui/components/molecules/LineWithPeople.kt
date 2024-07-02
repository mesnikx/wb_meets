package com.example.first_week_creating_ui_kit.ui.components.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.first_week_creating_ui_kit.ui.components.atoms.AvatarType
import com.example.first_week_creating_ui_kit.ui.components.atoms.CustomAvatar
import com.example.first_week_creating_ui_kit.ui.row.OverlappingRow
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.firstweek_lessonfirst.R

@Composable
fun LineWithPeople(
    avatars: List<String?>,
    modifier: Modifier = Modifier,
    avatarsNum: Int = 5,
    size: Dp = 48.dp
) {
    Box(
        modifier = modifier
            .padding(AppTheme.dimens.padding2dp),
        contentAlignment = Alignment.Center
    ) {
        if (avatars.isEmpty()) {
            Text(text = stringResource(id = R.string.linePeople))
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OverlappingRow(overlappingPercentage = 0.24f) {
                    avatars.take(avatarsNum).forEach { avatar ->
                        if (avatar == null) {
                            CustomAvatar(
                                type = AvatarType.AvatarMeeting,
                                isEditable = false,
                                haveBorder = true,
                                size = size
                            )

                        } else {
                            CustomAvatar(
                                type = AvatarType.AvatarMeeting,
                                imageUri = avatar,
                                isEditable = false,
                                haveBorder = true,
                                size = size
                            )
                        }
                    }
                }
                if (avatars.size > avatarsNum) {
                    Text(
                        modifier = Modifier.padding(start = AppTheme.dimens.padding8dp),
                        text = "+${avatars.size - avatarsNum}",
                        style = AppTheme.typo.bodyText1,
                        color = AppTheme.colors.neutralColorFont
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowLineWithPeople() {
    LineWithPeople(avatars = listOf(
        "https://avatars.mds.yandex.net/i?id=e24e74912cbd10b96d35b5bb7249977abfc96b5b-7012253-images-thumbs&n=13",
        null,
        null,
        null,
        null,
        null,
        null)
    )
}