package com.example.first_week_creating_ui_kit.ui.components.screens.autorization.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.example.first_week_creating_ui_kit.ui.theme.AppTheme

const val HINT = "000 000-00-00"

@Composable
fun PhoneField(
    phone: String,
    modifier: Modifier = Modifier,
    mask: String = HINT,
    maskNumber: Char = '0',
    onPhoneChanged: (String) -> Unit
) {
    BasicTextField(
        value = phone,
        onValueChange = { it ->
            onPhoneChanged(it.take(mask.count { it == maskNumber }))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = PhoneVisualTransformation(),
        modifier = modifier
            .background(AppTheme.colors.neutralColorSecondaryBackground)
            .padding(
                horizontal = AppTheme.dimens.paddingMedium,
                vertical = AppTheme.dimens.paddingXMedium
            )
            .clip(RoundedCornerShape(AppTheme.dimens.paddingMedium)),
        textStyle = AppTheme.typo.bodyText1,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                if (phone.isBlank()) {
                    Text(
                        text = mask,
                        color = AppTheme.colors.neutralColorSecondaryText,
                        style = AppTheme.typo.bodyText1
                    )
                }
                innerTextField()
            }
        },
        singleLine = true
    )
}

class PhoneVisualTransformation : VisualTransformation {
    val mask = HINT
    val maskNumber = '0'
    val maxLength = this.mask.count { it == this.maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

class PhoneOffsetMapper(private val mask: String, private val numberChar: Char) :
    OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != numberChar }
}

