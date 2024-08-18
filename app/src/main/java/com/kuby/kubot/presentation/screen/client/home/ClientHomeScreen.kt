package com.kuby.kubot.presentation.screen.client.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kuby.kubot.presentation.navgation.graph.client.ClientNavGraph
import com.kuby.kubot.presentation.screen.client.home.components.ClientBottomBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClientHomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { ClientBottomBar(navController = navController) }
    ) {
        ClientNavGraph(navController = navController)
    }
}