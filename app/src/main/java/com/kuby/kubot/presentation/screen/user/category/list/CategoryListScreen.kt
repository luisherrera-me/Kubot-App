package com.kuby.kubot.presentation.screen.user.crops.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


@Composable
fun CategoryListScreen(navController: NavHostController) {
    Scaffold {
        Text(
            modifier = Modifier.padding(it),
            text = "Crop List"
        )
    }
}