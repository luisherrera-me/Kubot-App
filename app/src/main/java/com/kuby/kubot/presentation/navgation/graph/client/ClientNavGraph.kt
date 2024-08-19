package com.kuby.kubot.presentation.navgation.graph.client

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.navgation.graph.profile.profileNavGraph
import com.kuby.kubot.presentation.navgation.screen.client.ClientScreen
import com.kuby.kubot.presentation.screen.client.Crops.list.ClientCropListScreen
import com.kuby.kubot.presentation.screen.client.dashboard.DashboardScreen
import com.kuby.kubot.presentation.screen.client.devices.list.ClientDeviceListScreen
import com.kuby.kubot.presentation.screen.profile.ProfileScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ClientNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.CLIENT,
        startDestination = ClientScreen.Dashboard.route
    ) {

        composable(route = ClientScreen.DeviceList.route) {
            ClientDeviceListScreen()
        }

        composable(route = ClientScreen.CropList.route) {
            ClientCropListScreen()
        }


        composable(route = ClientScreen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(route = ClientScreen.Dashboard.route) {
            DashboardScreen(navController)
        }


        profileNavGraph(navController)

    }
}