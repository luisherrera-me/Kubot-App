package com.kuby.kubot.presentation.screen.client.Crops.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.kuby.kubot.presentation.screen.client.Crops.list.components.ClientCropListContent

@Composable
fun ClientCropListScreen() {

    Scaffold() {paddingValues ->
        ClientCropListContent(paddingValues = paddingValues)
    }

}