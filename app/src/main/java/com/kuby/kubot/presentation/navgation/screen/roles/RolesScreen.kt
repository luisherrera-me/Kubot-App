package com.kuby.kubot.presentation.navgation.screen.roles

sealed class RolesScreen(val route: String) {
    object Roles: RolesScreen("roles")
}
