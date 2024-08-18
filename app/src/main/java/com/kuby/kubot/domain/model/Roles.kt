package com.kuby.kubot.domain.model

import kotlinx.serialization.Serializable
import okhttp3.Route

@Serializable
data class Roles(
    val id: String,
    val name: String,
    val image: String,
    val route: String
)
