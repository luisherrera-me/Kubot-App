package com.kuby.kubot.presentation.screen.auth.login

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kuby.kubot.domain.model.ApiRequest
import com.kuby.kubot.domain.model.ApiResponse
import com.kuby.kubot.presentation.navgation.screen.auth.AuthScreen
import com.kuby.kubot.presentation.screen.auth.login.components.Login
import com.kuby.kubot.presentation.screen.common.StartActivityForResult
import com.kuby.kubot.presentation.screen.common.signIn
import com.kuby.kubot.presentation.screen.auth.login.components.LoginContent
import com.kuby.kubot.util.RequestState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val signedInState by loginViewModel.signedInState
    val logInState by loginViewModel.LogInState
    val messageBarState by loginViewModel.messageBarState
    val apiResponse by loginViewModel.apiResponse

    Log.d("LoginScreen", apiResponse.toString())

    Scaffold(
        topBar = {
            //LoginTopBar()
        },
        content = {
            LoginContent(
                signedInState = signedInState,
                messageBarState = messageBarState,
                logInState = logInState,
                navController = navController,
                onButtonClicked = {
                    loginViewModel.saveSignedInState(signedIn = true)
                }
            )
        }
    )

    val activity = LocalContext.current as Activity
    
    Login(navController = navController)

    StartActivityForResult(
        key = signedInState,
        onResultReceived = { tokenId ->
            loginViewModel.verifyTokenOnBackend(
                request = ApiRequest(tokenId = tokenId)
            )
        },
        onDialogDismissed = {
            loginViewModel.saveSignedInState(signedIn = false)
        }
    ) { activityLauncher ->
        if (signedInState) {
            signIn(
                activity = activity,
                launchActivityResult = { intentSenderRequest ->
                    activityLauncher.launch(intentSenderRequest)
                },
                accountNotFound = {
                    loginViewModel.saveSignedInState(signedIn = false)
                    loginViewModel.updateMessageBarState()
                }
            )
        }
    }

    LaunchedEffect(key1 = apiResponse) {
        when (apiResponse) {
            is RequestState.Success -> {
                val response = (apiResponse as RequestState.Success<ApiResponse>).data.success
                if (response) {
                    loginViewModel.saveSignedInState(signedIn = false)
                    navigateToProfileScreen(navController = navController)
                } else {
                    loginViewModel.saveSignedInState(signedIn = false)
                }
            }
            else -> {}
        }
    }
}

private fun navigateToProfileScreen(
    navController: NavHostController
) {
    navController.navigate(route = AuthScreen.Profile.route) {
        popUpTo(route = AuthScreen.Login.route) {
            inclusive = true
        }
    }
}