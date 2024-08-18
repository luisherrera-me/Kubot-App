package com.kuby.kubot.data.repository.dataSource

import com.kuby.kubot.domain.model.LoginResponse
import com.kuby.kubot.domain.model.User
import com.kuby.kubot.domain.model.AuthResponse
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun login(loginResponse : LoginResponse): Response<AuthResponse>
    suspend fun register(user : User): Response<AuthResponse>
}