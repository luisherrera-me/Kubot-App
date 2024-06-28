package com.kuby.kubot.presentation.screen.auth.register

data class RegisterState (
    val name: String = "",
    val lastName: String = "",
    val telephone: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val showPassword: Boolean = false,
    val showConfirmPassword: Boolean = false,
)