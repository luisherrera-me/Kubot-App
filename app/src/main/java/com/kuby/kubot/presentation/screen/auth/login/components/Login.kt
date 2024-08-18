package com.kuby.kubot.presentation.screen.auth.login.components

import android.util.Log
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
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.screen.auth.login.LoginViewModel
import com.kuby.kubot.util.Resource

@Composable
fun Login (
    navController: NavHostController,
    vm: LoginViewModel = hiltViewModel()){
    when(val response = vm.loginResponse){
        Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            vm.saveSession(response.data)
            Log.d("Login", response.data.toString())
            vm.getSession()
            navController.navigate(route = Graph.CLIENT) {
                popUpTo(Graph.AUTH) {
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

