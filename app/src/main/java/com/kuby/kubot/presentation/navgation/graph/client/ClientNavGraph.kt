package com.kuby.kubot.presentation.navgation.graph.client

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.navgation.graph.profile.ProfileNavGraph
import com.kuby.kubot.presentation.navgation.screen.client.ClientScreen
import com.kuby.kubot.presentation.screen.client.category.list.ClientCategoryListScreen
import com.kuby.kubot.presentation.screen.client.product.list.ClientProductListScreen
import com.kuby.kubot.presentation.screen.profile.ProfileScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ClientNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.CLIENT,
        startDestination = ClientScreen.ProductList.route
    ) {

        composable(route = ClientScreen.ProductList.route) {
            ClientProductListScreen()
        }

        composable(route = ClientScreen.CategoryList.route) {
            ClientCategoryListScreen()
        }


        composable(route = ClientScreen.Profile.route) {
            ProfileScreen(navController)
        }


        ProfileNavGraph(navController)

    }
}