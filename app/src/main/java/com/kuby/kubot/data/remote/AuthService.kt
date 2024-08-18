package com.kuby.kubot.data.remote

import com.kuby.kubot.domain.model.ApiRequest
import com.kuby.kubot.domain.model.ApiResponse
import com.kuby.kubot.domain.model.LoginResponse
import com.kuby.kubot.domain.model.User
import com.kuby.kubot.domain.model.AuthResponse
import com.kuby.kubot.domain.model.UserUpdate
import retrofit2.Response
import retrofit2.http.*


interface AuthService {

    @POST("/token_verification")
    suspend fun verifyTokenOnBackend(
        @Body request: ApiRequest
    ): ApiResponse

    @POST("/api/v1/auth/sign_in")
    suspend fun login(
        @Body request: LoginResponse
    ): Response<AuthResponse>

    @POST("/api/v1/auth/sign_up")
    suspend fun register(
        @Body request: User
    ): Response<AuthResponse>

    @GET("/get_user")
    suspend fun getUserInfo(): ApiResponse

    @PUT("/update_user")
    suspend fun updateUser(
        @Body userUpdate: UserUpdate
    ): ApiResponse

    @DELETE("/delete_user")
    suspend fun deleteUser(): ApiResponse

    @GET("/sign_out")
    suspend fun clearSession(): ApiResponse

}