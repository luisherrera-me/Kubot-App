package com.kuby.kubot.presentation.navgation.graph.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.navgation.graph.auth.authNavGraph
import com.kuby.kubot.presentation.navgation.graph.roles.RolesNavGraph



@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController = navController)
        RolesNavGraph(navController = navController)
    }
}