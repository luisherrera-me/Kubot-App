package com.kuby.kubot.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kuby.kubot.R
import com.kuby.kubot.presentation.ui.theme.KubotTheme
import com.kuby.kubot.util.UiText

@Composable
fun EmailField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    label: String? = UiText.Res(R.string.emailField_label).get, // if this is null, label is not shown.
    @Suppress("UNUSED_PARAMETER") // left for future use
    labelComponent: @Composable (() -> Unit)? =
        { Text(text = label ?: UiText.Res(R.string.emailField_label).get) },
    placeholder: String = UiText.Res(R.string.emailField_placeholder).get,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
    keyboardActions: KeyboardActions? = null,
) {
    val focusManager = LocalFocusManager.current
    val keyboardActionsLocal: KeyboardActions = keyboardActions
        ?: KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
    )

    TextEntryField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        label = label,
        placeholder = placeholder,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActionsLocal,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Email,
                UiText.Res(R.string.emailField_description_email).get)
        },
        validInputDescription = UiText.Res(R.string.emailField_description_isValid).get,
        invalidInputDescription = UiText.Res(R.string.emailField_description_isInvalid).get
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EmailFieldPreview() {
    KubotTheme {
        EmailField(
            value = "",
            label = null,
            isError = false,
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EmailFieldPreviewError() {
    KubotTheme {
        EmailField(
            value = "Bad.Email",
            isError = true,
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EmailFieldPreviewValid() {
    KubotTheme {
        EmailField(
            value = "chris@demo.com",
            isError = false,
            onValueChange = {}
        )
    }
}















