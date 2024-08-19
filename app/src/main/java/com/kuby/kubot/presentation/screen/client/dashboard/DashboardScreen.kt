package com.kuby.kubot.presentation.screen.client.dashboard

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kuby.kubot.presentation.screen.client.Crops.list.components.ClientCropListContent
import com.kuby.kubot.presentation.screen.client.dashboard.components.DashboardContent

@Composable
fun DashboardScreen(navController: NavHostController) {

    Scaffold() {paddingValues ->
        DashboardContent(paddingValues = paddingValues, navController = navController)
    }
}