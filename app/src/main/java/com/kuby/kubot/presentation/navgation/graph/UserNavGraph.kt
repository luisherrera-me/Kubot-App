package com.kuby.kubot.presentation.navgation.graph

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.kuby.kubot.presentation.navgation.Graph
import com.kuby.kubot.presentation.navgation.screen.user.UserScreen
import com.kuby.kubot.presentation.screen.user.crops.list.CategoryListScreen
import com.kuby.kubot.presentation.screen.user.crops.list.CropListScreen
import com.kuby.kubot.presentation.screen.user.profile.ProfileScreen
import com.kuby.kubot.presentation.screen.user.robots.list.RobotListScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.USER,
        startDestination = UserScreen.CropList.route
    ){
        composable(route = UserScreen.CropList.route){
            CropListScreen(navController = navController)
        }
        composable(route = UserScreen.RobotList.route){
            RobotListScreen(navController = navController)
        }
        composable(route = UserScreen.CategoryList.route){
            CategoryListScreen(navController = navController)
        }
        composable(route = UserScreen.Profile.route){
            ProfileScreen(navController = navController)
        }

    }
}