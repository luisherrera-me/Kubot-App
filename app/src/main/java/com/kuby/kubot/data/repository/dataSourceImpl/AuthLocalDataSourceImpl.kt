package com.kuby.kubot.data.repository.dataSourceImpl

import com.kuby.kubot.data.datastore.AuthDatastore
import com.kuby.kubot.data.repository.dataSource.AuthLocalDataSource
import com.kuby.kubot.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow

class AuthLocalDataSourceImpl constructor(private val authDatastore: AuthDatastore): AuthLocalDataSource {

    override suspend fun saveSession(authResponse: AuthResponse) = authDatastore.saveUser(authResponse)
    override fun getSession(): Flow<AuthResponse> = authDatastore.getData()
    override suspend fun deleteSession() = authDatastore.deleteUser()

}