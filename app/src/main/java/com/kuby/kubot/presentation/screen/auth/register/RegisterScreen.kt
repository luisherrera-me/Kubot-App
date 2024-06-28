package com.kuby.kubot.presentation.screen.auth.register


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kuby.kubot.presentation.screen.auth.register.components.RegisterContent

@Composable
fun RegisterScreen(
    navController: NavHostController,
    registerViewModel: RegisterViewModel = hiltViewModel()
){

    val messageBarState by registerViewModel.messageBarState

    Scaffold(){
        RegisterContent(
            Modifier.padding(it),
            navController = navController,
            messageBarState = messageBarState

        )
    }
}