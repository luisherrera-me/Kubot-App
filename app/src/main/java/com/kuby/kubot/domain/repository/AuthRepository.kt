package com.kuby.kubot.domain.repository

import com.kuby.kubot.domain.model.LoginResponse
import com.kuby.kubot.domain.model.User
import com.kuby.kubot.domain.model.AuthResponse
import com.kuby.kubot.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginResponse: LoginResponse): Resource<AuthResponse>
    suspend fun register(user: User): Resource<AuthResponse>

    suspend fun saveSession(authResponse: AuthResponse)
    fun getSessionData(): Flow<AuthResponse>
}