package com.kuby.kubot.data.repository

import android.util.Log
import com.kuby.kubot.data.remote.AuthService
import com.kuby.kubot.domain.model.ApiRequest
import com.kuby.kubot.domain.model.ApiResponse
import com.kuby.kubot.domain.model.UserUpdate
import com.kuby.kubot.domain.repository.DataStoreOperations
import com.kuby.kubot.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val ktorApi: AuthService
): Repository{
    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStoreOperations.saveSignedInState(signedIn = signedIn)
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStoreOperations.readSignedInState()
    }

    override suspend fun verifyTokenOnBackend(request: ApiRequest): ApiResponse {
        val data = try {
            ktorApi.verifyTokenOnBackend(request = request)
        } catch (e: Exception) {
            ApiResponse(success = false, error = e)
        }
        Log.e("RepositoryImpl", "verifyTokenOnBackend: $data")
        return data
    }

    override suspend fun getUserInfo(): ApiResponse {
        return try {
            ktorApi.getUserInfo()
        } catch (e: Exception) {
            return ApiResponse(success = false, error = e)
        }
    }

    override suspend fun updateUser(userUpdate: UserUpdate): ApiResponse {
        return try {
            ktorApi.updateUser(userUpdate = userUpdate)
        } catch (e: Exception) {
            ApiResponse(success = false, error = e)
        }
    }

    override suspend fun deleteUser(): ApiResponse {
        return try {
            ktorApi.deleteUser()
        } catch (e: Exception) {
            ApiResponse(success = false, error = e)
        }

    }

    override suspend fun clearSession(): ApiResponse {
        return try {
            ktorApi.clearSession()
        } catch (e: Exception) {
            ApiResponse(success = false, error = e)
        }
    }
}