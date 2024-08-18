package com.kuby.kubot.data.repository.dataSource

import com.kuby.kubot.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveSession(authResponse: AuthResponse)
    fun getSession(): Flow<AuthResponse>

}