package com.kuby.kubot.data.repository

import com.kuby.kubot.data.repository.dataSource.AuthLocalDataSource
import com.kuby.kubot.data.repository.dataSource.AuthRemoteDataSource
import com.kuby.kubot.domain.model.ErrorResponse
import com.kuby.kubot.domain.model.LoginResponse
import com.kuby.kubot.domain.model.User
import com.kuby.kubot.domain.model.AuthResponse
import com.kuby.kubot.domain.repository.AuthRepository
import com.kuby.kubot.util.ConvertErrorBody
import com.kuby.kubot.util.Resource
import com.kuby.kubot.util.ResponseToRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
): AuthRepository {

    override suspend fun login(
        loginResponse: LoginResponse
    ): Resource<AuthResponse> = ResponseToRequest.send(
        authRemoteDataSource.login(loginResponse)
    )

    override suspend fun register(
        user: User
    ): Resource<AuthResponse> = ResponseToRequest.send(
        authRemoteDataSource.register(user)
        )

    override suspend fun deleteSession() = authLocalDataSource.deleteSession()

    override suspend fun saveSession(authResponse: AuthResponse) = authLocalDataSource.saveSession(authResponse)

    override fun getSessionData(): Flow<AuthResponse> = authLocalDataSource.getSession()

}