package com.kuby.kubot.presentation.screen.client.Crops.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ClientCropListContent(paddingValues: PaddingValues) {
    Text(
        modifier = Modifier.padding(paddingValues = paddingValues),
        text = "crop List Screen"
    )
}