package com.kuby.kubot.presentation.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.kuby.kubot.presentation.ui.theme.borderlightGrey
import com.kuby.kubot.presentation.ui.theme.darkGrey
import com.kuby.kubot.presentation.ui.theme.lightBlue
import com.kuby.kubot.presentation.ui.theme.lightGrey


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(fontFamily = FontFamily(Font(R.font.poppinsmedium))),
    keyboardType: KeyboardType = KeyboardType.Text,
    textValue: String,
    icon: ImageVector = Icons.Outlined.MailOutline,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    colorBackground: Color = lightGrey,
    disabledLabelColor: Color = borderlightGrey,
    enabled: Boolean = true
) {
    TextField(
        modifier = modifier,
        enabled = enabled,
        leadingIcon = {
            androidx.compose.material.Icon(
                imageVector = icon,
                tint = darkGrey,
                contentDescription = "Email Icon"
            )
        },
        textStyle = textStyle,
        value = textValue,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorBackground,
            cursorColor = Color.Black,
            disabledLabelColor = disabledLabelColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = false,
        placeholder = {
            Text(
                text = placeholderText,
                fontWeight = FontWeight.Normal
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        )
    )

}

@Composable
@Preview
fun DefaultTextFieldPreview(){
    var text by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    CustomTextField(
        keyboardType = KeyboardType.Password,
        textValue = text,
        onValueChange = { text = it },
        placeholderText = "Enter your text"
    )
}