package com.kuby.kubot.presentation.screen.client.product.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.kuby.kubot.presentation.screen.client.product.list.components.ClientProductListContent

@Composable
fun ClientProductListScreen() {

    Scaffold() {paddingValues ->
        ClientProductListContent(paddingValues = paddingValues)
    }

}