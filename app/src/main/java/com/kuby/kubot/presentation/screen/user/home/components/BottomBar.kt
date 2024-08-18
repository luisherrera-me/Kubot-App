package com.kuby.kubot.presentation.screen.user.home.components


import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kuby.kubot.presentation.navgation.screen.user.UserScreen


@Composable
fun BottomBar (navController: NavHostController) {

    val screen = listOf(
        UserScreen.Profile,
        UserScreen.CropList,
        UserScreen.RobotList,
        UserScreen.CategoryList
    )
    val navBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackEntry?.destination
    val bottomBarDestination = screen.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation() {
            screen.forEach { screen ->
                BottonBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }


}