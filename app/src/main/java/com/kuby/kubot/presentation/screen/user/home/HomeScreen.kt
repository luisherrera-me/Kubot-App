package com.kuby.kubot.presentation.screen.user.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kuby.kubot.presentation.navgation.graph.UserNavGraph
import com.kuby.kubot.presentation.screen.user.home.components.BottomBar


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        UserNavGraph(navController = navController)
    }
}