package com.kuby.kubot.domain.repository

import com.kuby.kubot.domain.model.ApiRequest
import com.kuby.kubot.domain.model.ApiResponse
import com.kuby.kubot.domain.model.UserUpdate
import kotlinx.coroutines.flow.Flow
import okhttp3.Request

interface Repository {
    suspend fun saveSignedInState(signedIn: Boolean)
    fun readSignedInState(): Flow<Boolean>
    suspend fun verifyTokenOnBackend(request: ApiRequest): ApiResponse
    suspend fun getUserInfo(): ApiResponse
    suspend fun updateUser(userUpdate: UserUpdate): ApiResponse
    suspend fun deleteUser(): ApiResponse
    suspend fun clearSession(): ApiResponse
}