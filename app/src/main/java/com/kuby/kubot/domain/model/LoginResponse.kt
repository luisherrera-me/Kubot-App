package com.kuby.kubot.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class LoginResponse(
    val emailAddress: String? = null,
    val password: String? = null,
)
