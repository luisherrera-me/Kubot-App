package com.kuby.kubot.presentation.screen.client.category.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.kuby.kubot.presentation.screen.client.category.list.components.ClientCategoryListContent

@Composable
fun ClientCategoryListScreen() {

    Scaffold() {paddingValues ->
        ClientCategoryListContent(paddingValues = paddingValues)
    }

}