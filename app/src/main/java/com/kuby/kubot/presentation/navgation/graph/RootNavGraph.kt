package com.kuby.kubot.presentation.navgation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.screen.auth.login.LoginScreen
import com.kuby.kubot.presentation.screen.profile.ProfileScreen


@OptIn(ExperimentalCoilApi::class)
@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController = navController)
    }
}