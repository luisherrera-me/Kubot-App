package com.kuby.kubot.presentation.navgation.screen.user

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector

sealed class UserScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object CropList: UserScreen(
        route = "user/Crops/list",
        title = "Crops",
        icon = Icons.Filled.AccountBox
    )

    data object RobotList: UserScreen(
        route = "user/robot/list",
        title = "Crops",
        icon = Icons.Filled.AccountBox
    )

    data object CategoryList: UserScreen(
        route = "user/category/list",
        title = "Crops",
        icon = Icons.Filled.AccountBox
    )

    data object Profile: UserScreen(
        route = "user/profile",
        title = "Crops",
        icon = Icons.Filled.Face
    )


}