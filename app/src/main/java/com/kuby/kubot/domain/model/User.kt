package com.kuby.kubot.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.LocalDateTime

@Serializable
data class User(
    val id: String? = null,
    val name: String? = null,
    val lastName: String? = null,
    val createdAt: String?= null,
    val updatedAt: String?= null,
    val notificationToken: String ?= null,
    val emailAddress: String? = null,
    val password: String? = null,
    val phone: String? = null,
    val profilePhoto: String? = null,
    val roles: ArrayList<Roles>? = null
)
{
    fun toJson(): String = Json.encodeToString(serializer(), this)

    companion object {
        fun fromJson(json: String): User = Json.decodeFromString(serializer(), json)
    }
}
