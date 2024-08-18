package com.kuby.kubot.presentation.screen.auth.register.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kuby.kubot.domain.model.MessageBarState
import com.kuby.kubot.presentation.navgation.screen.auth.AuthScreen
import com.kuby.kubot.presentation.screen.auth.login.LoginViewModel
import com.kuby.kubot.presentation.screen.auth.register.RegisterViewModel
import com.kuby.kubot.util.Resource

@Composable
fun Register (
    navController: NavHostController,
    vm: RegisterViewModel = hiltViewModel()){
    when(val response = vm.registerResponse){
        Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            vm.saveSession(response.data)
            navController.navigate(route = AuthScreen.Profile.route) {
                popUpTo(route = AuthScreen.Login.route) {
                    inclusive = true
                }
            }
        }
        is Resource.Error -> {
            vm._messageBarState.value = MessageBarState(
                message = response.exception.message,
                error = Exception(response.exception.message)
            )

            Toast.makeText(LocalContext.current, response.exception?.message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
        }
        else -> Unit
    }

}

