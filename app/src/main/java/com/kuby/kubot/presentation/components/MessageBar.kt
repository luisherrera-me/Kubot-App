package com.kuby.kubot.presentation.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import com.kuby.kubot.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuby.kubot.domain.model.MessageBarState
import com.kuby.kubot.presentation.ui.theme.ErrorRed
import com.kuby.kubot.presentation.ui.theme.InfoGreen
import kotlinx.coroutines.delay
import java.net.ConnectException
import java.net.SocketTimeoutException


@Composable
fun MessageBar(messageBarState: MessageBarState) {
    var startAnimation by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = messageBarState) {
        if (messageBarState.error != null) {
            errorMessage = when (messageBarState.error) {
                is SocketTimeoutException -> {
                    "Connection Timeout Exception."
                }
                is ConnectException -> {
                    "Internet Connection Unavailable."
                }
                else -> {
                    "${messageBarState.error.message}"
                }
            }
        }
        startAnimation = true
        delay(3000)
        startAnimation = false
    }

    AnimatedVisibility(
        visible = messageBarState.error != null && startAnimation
                || messageBarState.message != null && startAnimation,
        enter = expandVertically(
            animationSpec = tween(300),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(300),
            shrinkTowards = Alignment.Top
        )
    ) {
        Message(
            messageBarState = messageBarState,
            errorMessage = errorMessage
        )
    }
}

@Composable
fun Message(
    messageBarState: MessageBarState,
    errorMessage: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, end = 12.dp, start = 12.dp)
            .clip(RoundedCornerShape(7.dp)) // Clip antes de background
            .background(if (messageBarState.error != null) ErrorRed else InfoGreen)
            .padding(horizontal = 20.dp)
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter =
            painterResource(id = if (messageBarState.error != null) R.drawable.alert_circle
            else R.drawable.checkmark_circle),
            contentDescription = "Message Bar Icon",
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text =
            if (messageBarState.error != null) errorMessage
            else messageBarState.message.toString(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.button.fontSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
@Preview
fun MessageBarPreview() {
    Message(
        messageBarState = MessageBarState(message = "Successfully Updated.")
    )
}

@Composable
@Preview
fun MessageBarErrorPreview() {
    Message(
        messageBarState = MessageBarState(error = SocketTimeoutException()),
        errorMessage = "Connection Timeout Exception."
    )
}