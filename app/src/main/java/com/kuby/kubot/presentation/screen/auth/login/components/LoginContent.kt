package com.kuby.kubot.presentation.screen.auth.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kuby.core.InternetConnectivityObserver.IInternetConnectivityObserver
import com.kuby.core.InternetConnectivityObserver.InternetAvailabilityIndicator
import com.kuby.kubot.R
import com.kuby.kubot.domain.model.MessageBarState
import com.kuby.kubot.presentation.component.EmailField
import com.kuby.kubot.presentation.component.PasswordField
import com.kuby.kubot.presentation.components.CustomTextField
import com.kuby.kubot.presentation.components.GoogleButton
import com.kuby.kubot.presentation.components.MessageBar
import com.kuby.kubot.presentation.components.TextFieldPass
import com.kuby.kubot.presentation.navgation.screen.auth.AuthScreen
import com.kuby.kubot.presentation.screen.auth.login.LoginViewModel
import com.kuby.kubot.presentation.ui.theme.RegularFont
import com.kuby.kubot.util.keyboardVisibilityObserver

@Composable
fun LoginContent(
    signedInState: Boolean,
    logInState: Boolean,
    navController: NavHostController,
    messageBarState: MessageBarState,
    onButtonClicked: () -> Unit,
    connectivityObserver: IInternetConnectivityObserver.OnlineStatus
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

                LoginScreenContent(
                    signedInState = signedInState,
                    navController = navController,
                    logInState = logInState,
                    onButtonClicked = onButtonClicked
                )
                MessageBar(messageBarState = messageBarState)
                InternetAvailabilityIndicator(connectivityObserver)
            }
        }
    }
}

@Composable
fun LoginScreenContent(
    vm: LoginViewModel = hiltViewModel(),
    signedInState: Boolean,
    logInState: Boolean,
    navController: NavHostController,
    onButtonClicked: () -> Unit
){

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isKeyboardOpen by keyboardVisibilityObserver()

    Column(
        modifier = Modifier
            //.fillMaxSize()
            .background(Color.Transparent)
            .padding(top = 220.dp, start = 30.dp, end = 30.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "log In",
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            color = Color.Black,
            fontFamily = RegularFont
        )
        Text(
            modifier = Modifier.padding(bottom = 15.dp),
            text = "Enter your access credentials",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Gray,
            fontFamily = RegularFont
        )

        EmailField(
            value = vm.state.email,
            label = null,
            onValueChange = { vm.onEmailInput(it) },
            isError = false
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordField(
            value = vm.state.password,
            onValueChange = { vm.onPasswordInput(it) },
            label = null,
            clickTogglePasswordVisibility = {vm.state.showPassword},
            isError = false
        )

        GoogleButton(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 60.dp, end = 60.dp),
            primaryText = "Login",
            secondaryText = "Sign Up ...",
            icon = R.drawable.ic_arrow_right,
            loadingState = logInState,
            onClick = { vm.login() }
        )

        Text(
            modifier = Modifier
                .padding(15.dp)
                .clickable { navController.navigate( route = AuthScreen.Register.route) },
            text = "New User? Sign Up ",
            fontWeight = FontWeight.ExtraLight,
            color = Color.Black,
            fontFamily = RegularFont
        )


        Text(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                ),
            text = "Or connect with",
            fontWeight = FontWeight.Medium, color = Color.Gray,
        )

        Spacer(modifier = Modifier.height(8.dp))

        GoogleButton(
            loadingState = signedInState,
            onClick = onButtonClicked
        )
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true )
fun LoginContentPreview(){
//    LoginContent(
//        signedInState = false,
//        messageBarState = MessageBarState(),
//        onButtonClicked = {},
//        logInState = false,
//        navController = rememberNavController()
//    )
}