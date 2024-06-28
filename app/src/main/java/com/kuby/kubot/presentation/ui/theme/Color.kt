package com.kuby.kubot.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material.Colors

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val lightBlue = Color(0xffd8e6ff)
val lightGrey = Color(0xFFCBFDE8)
val borderlightGrey = Color(0xFF9DFFD6)
val darkGrey = Color(0xFF333333)
val LoadingBlue = Color(0xFF307CFF)
val ErrorRed = Color(0xFFFA5252)
val InfoGreen = Color(0xFF77FF9E)
val buttonColor = Color(0xFF919191)

val Colors.topAppBarContentColor: Color
    get() = if (isLight) Color.White else Color.Black

val Colors.textColor: Color
    get() = if (isLight) Color.Black else Color.White

val Colors.topAppBarBackgroundColor: Color
    get() = if (isLight) Purple40 else Color.Black


