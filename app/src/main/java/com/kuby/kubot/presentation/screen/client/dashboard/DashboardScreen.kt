package com.kuby.kubot.presentation.screen.client.dashboard

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.kuby.kubot.presentation.screen.client.Crops.list.components.ClientCropListContent
import com.kuby.kubot.presentation.screen.client.dashboard.components.DashboardContent

@Composable
fun DashboardScreen() {

    Scaffold() {paddingValues ->
        DashboardContent(paddingValues = paddingValues)
    }
}