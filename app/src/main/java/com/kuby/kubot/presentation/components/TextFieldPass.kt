package com.kuby.kubot.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuby.kubot.R
import com.kuby.kubot.presentation.ui.theme.darkGrey
import com.kuby.kubot.presentation.ui.theme.lightBlue
import com.kuby.kubot.presentation.ui.theme.lightGrey

@Composable
fun TextFieldPass(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(fontFamily = FontFamily(Font(R.font.poppinsmedium))),
    keyboardType: KeyboardType = KeyboardType.Password,
    textValue: String,
    onValueChange: (String) -> Unit,
    showPassword: Boolean,
    colorBackground: Color = lightGrey,
    disabledLabelColor: Color = lightBlue,
    onTogglePasswordVisibility: () -> Unit,
    placeholderText: String = "Password"
){
    TextField(
        modifier = modifier,
        textStyle = textStyle,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                tint = darkGrey,
                contentDescription = "Lock Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = onTogglePasswordVisibility) {
                Icon(
                    painter = painterResource(id = if (showPassword) R.drawable.ic_eye_password else R.drawable.ic_eye_password_off),
                    contentDescription = if (showPassword) "Show Password" else "Hide Password",
                    tint = if (showPassword) Color.Black else Color.Gray,
                    modifier = Modifier.size(24.dp) // Ajusta el tamaño según tus necesidades
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        value = textValue,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorBackground,
            cursorColor = Color.Black,
            disabledLabelColor = disabledLabelColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        placeholder = {
            Text(
                text = placeholderText,
                fontWeight = FontWeight.Normal
            )
        }
    )
}