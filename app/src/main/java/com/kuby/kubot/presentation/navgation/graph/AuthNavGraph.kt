package com.kuby.kubot.presentation.navgation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.navgation.screen.auth.AuthScreen
import com.kuby.kubot.presentation.screen.auth.login.LoginScreen
import com.kuby.kubot.presentation.screen.auth.register.RegisterScreen
import com.kuby.kubot.presentation.screen.profile.ProfileScreen

@OptIn(ExperimentalCoilApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
        ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navController = navController)
        }

        composable(route = AuthScreen.Register.route){
            RegisterScreen(navController = navController)
        }
        composable(route = AuthScreen.Profile.route){
            ProfileScreen(navController = navController)
        }
    }
}