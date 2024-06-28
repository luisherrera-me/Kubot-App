package com.kuby.kubot.presentation.screen.auth.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false
)
