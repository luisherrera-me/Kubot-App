package com.kuby.kubot.presentation.screen.client.devices.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.kuby.kubot.presentation.screen.client.devices.list.components.ClientDeviceListContent

@Composable
fun ClientDeviceListScreen() {

    Scaffold() {paddingValues ->
        ClientDeviceListContent(paddingValues = paddingValues)
    }

}