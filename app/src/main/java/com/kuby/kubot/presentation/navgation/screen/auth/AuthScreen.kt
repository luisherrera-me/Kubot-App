package com.kuby.kubot.presentation.navgation.screen.auth



sealed class AuthScreen (val route: String){
    object Login: AuthScreen(route = "login_screen")
    object Register: AuthScreen(route = "Register_screen")
    object Profile: AuthScreen(route = "profile_screen")

}