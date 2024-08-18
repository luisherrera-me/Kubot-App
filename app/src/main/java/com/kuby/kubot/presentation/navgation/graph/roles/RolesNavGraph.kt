package com.kuby.kubot.presentation.navgation.graph.roles

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.navgation.screen.roles.RolesScreen
import com.kuby.kubot.presentation.screen.client.home.ClientHomeScreen

fun NavGraphBuilder.RolesNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ROLES,
        startDestination = RolesScreen.Roles.route
    ) {
        composable(route = Graph.CLIENT) {
            ClientHomeScreen()
        }
    }
}