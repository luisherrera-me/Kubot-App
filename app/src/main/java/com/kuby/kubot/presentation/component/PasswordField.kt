package com.kuby.kubot.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.kuby.kubot.R
import com.kuby.kubot.presentation.ui.theme.textEntryFieldTextStyle
import com.kuby.kubot.util.UiText

@Composable
fun PasswordField(
    modifier: Modifier = Modifier.fillMaxWidth(1f),
    value: String,
    textStyle: TextStyle = textEntryFieldTextStyle(),
    label: String? = UiText.Res(R.string.passwordField_label).get,
    labelComponent: @Composable (() -> Unit)? = {
        Text(
            text = label ?: UiText.Res(R.string.passwordField_label).get
        )
    },
    placeholder: String = UiText.Res(R.string.passwordField_placeholder).get,
    isError: Boolean,
    isPasswordVisible: Boolean = false,
    clickTogglePasswordVisibility: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Done,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = imeAction),
    keyboardActions: KeyboardActions? = null,
    doneAction: () -> Unit = {},
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardActionsLocal: KeyboardActions = keyboardActions ?: KeyboardActions(
        onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        },
        onDone = {
            focusManager.clearFocus()
            doneAction()
        }
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        isError = isError,
        label = if (label != null) labelComponent else null,
        placeholder = {
            Text(
                text = placeholder,
                style = textStyle.copy(color = textStyle.color.copy(alpha = 0.5f))
            )
        },
        modifier = modifier,
        visualTransformation =
        if (isPasswordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActionsLocal,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                UiText.Res(R.string.passwordField_description_lock).get
            )
        },
        singleLine = true,
        trailingIcon = {
            val isPasswordValid = !isError

            val validImage =
                if (isPasswordValid)
                    Icons.Filled.Check
                else
                    Icons.Filled.Error

            // localized description for accessibility services
            val validDescription =
                if (isPasswordValid)
                    UiText.Res(R.string.emailField_description_isValid).get
                else
                    UiText.Res(R.string.emailField_description_isInvalid).get

            val passwordVisibleImage = if (isPasswordVisible)
                Icons.Default.Visibility
            else
                Icons.Default.VisibilityOff

            // localized description for accessibility services
            val passwordVisibleDescription = if (isPasswordVisible)
                UiText.Res(R.string.passwordField_description_hide).get
            else
                UiText.Res(R.string.passwordField_description_show).get

            Row {
                if (value.isNotBlank()) {
                    Icon(
                        imageVector = validImage,
                        contentDescription = validDescription,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }

                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                    clickTogglePasswordVisibility(isPasswordVisible)
                }) {
                    Icon(imageVector = passwordVisibleImage, passwordVisibleDescription)
                }
            }
        }
    )
}
