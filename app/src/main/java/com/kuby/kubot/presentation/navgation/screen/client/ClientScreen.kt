package com.kuby.kubot.presentation.navgation.screen.client


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ClientScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object CropList: ClientScreen(
        route = "client/crop/list",
        title = "Crop",
        icon = Icons.Default.List
    )
    data object DeviceList: ClientScreen(
        route = "client/device/list",
        title = "Device",
        icon = Icons.Default.ThumbUp
    )

    data object Profile: ClientScreen(
        route = "client/profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )

    data object Dashboard: ClientScreen(
        route = "client/dashboard",
        title = "Dashboard",
        icon = Icons.Default.Refresh
    )



}

