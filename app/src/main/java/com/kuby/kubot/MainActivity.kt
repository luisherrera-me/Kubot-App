package com.kuby.kubot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kuby.kubot.presentation.navgation.graph.RootNavGraph
import com.kuby.kubot.presentation.ui.theme.KubotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //val navController = rememberNavController()
            //RootNavGraph(navController = navController)
            KubotTheme {
                navController = rememberNavController()
                RootNavGraph(navController = navController)
            }
        }
    }
}
