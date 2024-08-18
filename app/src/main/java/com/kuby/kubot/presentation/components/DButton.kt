package com.kuby.kubot.presentation.components



import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kuby.kubot.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kuby.kubot.presentation.ui.theme.InfoGreen
import com.kuby.kubot.presentation.ui.theme.RegularFont
import com.kuby.kubot.presentation.ui.theme.textColor
import com.kuby.kubot.presentation.ui.theme.topAppBarContentColor



@Composable
fun DButton(
    modifier: Modifier = Modifier,
    loadingState: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    icon: Int = R.drawable.ic_google,
    shape: Shape = RoundedCornerShape(7.dp), // Aquí defines el shape con bordes redondeados
    borderColor: Color = Color.Transparent,
    backgroundColor: Color = Color.Black,
    borderStroke: Dp = 0.dp,
    progressIndicatorColor: Color = InfoGreen,
    onClick: () -> Unit
){
    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(key1 = loadingState){
        buttonText = if (loadingState) secondaryText else primaryText
    }

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black, contentColor = Color.Black
        ),
        shape = RoundedCornerShape(7.dp)
    ) {
        Row (
            modifier = Modifier
                .padding(
                    start = 0.dp,
                    end = 0.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                painter =  painterResource(id = icon),
                contentDescription = "google logo",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = buttonText,
                //fontSize = MaterialTheme.typography.subtitle2.fontSize,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
            if(loadingState){
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
            }
        }
    }
}


@Composable
@Preview
fun BButtonPreview(){
    DButton(primaryText = "Next", icon = R.drawable.ic_arrow_right, onClick = {})
}
