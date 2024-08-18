package com.kuby.kubot.presentation.screen.client.devices.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClientDeviceListContent(paddingValues: PaddingValues) {
    Text(
        modifier = Modifier.padding(paddingValues = paddingValues)
            .padding(vertical = 50.dp)
        ,
        text = "Client Product List Screen"
    )
}