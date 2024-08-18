package com.kuby.kubot.presentation.screen.client.home.components

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kuby.kubot.presentation.navgation.screen.client.ClientScreen

@Composable
fun ClientBottomBar(navController: NavHostController) {

    val screens = listOf(
        ClientScreen.Dashboard,
        ClientScreen.DeviceList,
        ClientScreen.CropList,
        ClientScreen.Profile,
    )

    val navBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation() {
            screens.forEach { screen ->
                ClientBottomBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

}