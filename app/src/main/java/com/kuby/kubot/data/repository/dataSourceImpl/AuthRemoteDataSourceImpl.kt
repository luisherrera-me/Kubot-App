package com.kuby.kubot.data.repository.dataSourceImpl

import com.kuby.kubot.data.remote.AuthService
import com.kuby.kubot.data.repository.dataSource.AuthRemoteDataSource
import com.kuby.kubot.domain.model.LoginResponse
import com.kuby.kubot.domain.model.User

class AuthRemoteDataSourceImpl(private val apiService: AuthService): AuthRemoteDataSource {
    override suspend fun login(loginResponse: LoginResponse) = apiService.login(loginResponse)
    override suspend fun register(user: User) = apiService.register(user)

}