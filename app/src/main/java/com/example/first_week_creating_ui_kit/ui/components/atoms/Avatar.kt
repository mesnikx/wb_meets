package com.example.first_week_creating_ui_kit.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme
import com.example.firstweek_lessonfirst.R

sealed interface AvatarType {
    data object AvatarProfile : AvatarType
    data object AvatarMeeting : AvatarType
}

@Composable
fun CustomAvatar(
    type: AvatarType,
    modifier: Modifier = Modifier,
    imageUri: String? = null,
    onClick: () -> Unit = {},
    isEditable: Boolean = false,
    haveBorder: Boolean = false,
    size: Dp = 100.dp,
    backgroundColor: Color = AppTheme.colors.neutralColorBackground
) {
    Box(
        modifier = modifier
            .size(size)

    ) {
        when (type) {
            AvatarType.AvatarProfile -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .background(backgroundColor)
                ) {
                    AsyncImage(
                        model = imageUri,
                        placeholder = painterResource(R.drawable.ic_avatar),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .then(
                                if (imageUri == null) Modifier
                                    .fillMaxSize(0.5f) else Modifier.fillMaxSize(1f)
                            ),
                        contentDescription = stringResource(R.string.avatar),
                        contentScale = if (imageUri == null) ContentScale.Fit else ContentScale.Crop
                    )
                }
                if (isEditable) {
                    IconButton(
                        onClick = onClick,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(AppTheme.dimens.paddingSmall)
                            .fillMaxSize(0.24f)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = stringResource(R.string.avatar),
                            tint = AppTheme.colors.neutralColorFont
                        )
                    }
                }
            }

            AvatarType.AvatarMeeting -> {

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(AppTheme.dimens.paddingXLarge))
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(AppTheme.dimens.paddingXLarge))
                            .then(
                                if (haveBorder) Modifier.border(
                                    width = AppTheme.dimens.paddingXSmall,
                                    shape = RoundedCornerShape(AppTheme.dimens.paddingXLarge),
                                    color = AppTheme.colors.gradientColorBackground
                                ) else Modifier
                            ),
                        model = imageUri,
                        contentDescription = stringResource(R.string.avatar),
                        placeholder = painterResource(R.drawable.ava_orange),
                        contentScale = ContentScale.Crop
                    )
                }
                if (isEditable) {
                    IconButton(
                        onClick = onClick,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .fillMaxSize(0.24f)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = stringResource(R.string.avatar),
                            tint = AppTheme.colors.neutralColorFont
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MyApp2() {
    Column(modifier = Modifier.padding(AppTheme.dimens.paddingXLarge)) {
        CustomAvatar(
            type = AvatarType.AvatarProfile,
            isEditable = true,
            modifier = Modifier
                .padding(bottom = AppTheme.dimens.paddingXLarge)
        )

        CustomAvatar(
            type = AvatarType.AvatarMeeting,
            isEditable = false,
            haveBorder = false,
            modifier = Modifier
                .padding(bottom = AppTheme.dimens.paddingXLarge)
        )

        CustomAvatar(
            type = AvatarType.AvatarProfile,
            imageUri = "https://avatars.dzeninfra.ru/get-zen_doc/1592433/pub_613232f67916e7006acd81cc_613238bd39f2cf24c3cb3303/scale_1200",
            isEditable = true,
            modifier = Modifier
                .padding(bottom = AppTheme.dimens.paddingXLarge)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp2() {
    MyApp2()
}