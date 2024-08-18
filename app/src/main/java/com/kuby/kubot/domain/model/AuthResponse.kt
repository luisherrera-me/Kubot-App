package com.kuby.kubot.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class AuthResponse(
    val user: User? = null,
    val token: String? = null,
) {
    fun toJson(): String = Json.encodeToString(serializer(), this)

    companion object {
        fun fromJson(json: String): AuthResponse = Json.decodeFromString(serializer(), json)
    }
}
