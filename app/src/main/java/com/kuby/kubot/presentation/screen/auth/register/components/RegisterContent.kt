package com.kuby.kubot.presentation.screen.auth.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import com.kuby.kubot.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.kuby.kubot.presentation.components.GoogleButton
import com.kuby.kubot.presentation.components.MessageBar
import com.kuby.kubot.domain.model.MessageBarState
import com.kuby.kubot.presentation.components.CustomTextField
import com.kuby.kubot.presentation.components.DButton
import com.kuby.kubot.presentation.components.TextFieldPass
import com.kuby.kubot.presentation.navgation.screen.auth.AuthScreen
import com.kuby.kubot.presentation.screen.auth.login.LoginViewModel
import com.kuby.kubot.presentation.screen.auth.register.RegisterViewModel
import com.kuby.kubot.presentation.ui.theme.RegularFont

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    messageBarState: MessageBarState,
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier){
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier.fillMaxWidth()){
                Image(
                    modifier = Modifier
                        .padding(bottom = 0.dp, top = 0.dp),
                    painter = painterResource(id = R.drawable.roral),
                    contentDescription = "Logo Google",
                    colorFilter = ColorFilter.colorMatrix(
                        ColorMatrix().apply {
                            setToScale(0.9f, 1f, 0.8f, 0.7f)
                        }
                    )
                )
                IconButton(
                    onClick = {  navController.navigate( route = AuthScreen.Login.route) },
                    modifier = Modifier
                        //.size(70.dp)
                        .padding(top = 50.dp, start = 10.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Home")
                }
                if (messageBarState.error != null) {
                    MessageBar(messageBarState = messageBarState)
                }else{
                    MessageBar(messageBarState = messageBarState)
                }
                CentralConent()

            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun  RegisterContentPreview(){
    //RegisterContent(
        //signedInState = true,
        //messageBarState = MessageBarState(),
        //onButtonClicked = {}
    //)
}