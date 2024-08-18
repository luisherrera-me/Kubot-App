package com.kuby.kubot.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kuby.kubot.domain.model.AuthResponse
import com.kuby.kubot.util.Constants.AUTH_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthDatastore  constructor(private val dataStore: DataStore<Preferences>){

    suspend fun saveUser(authResponse: AuthResponse){
        val datastoreKey = stringPreferencesKey(AUTH_KEY)
        dataStore.edit {pref ->
            pref[datastoreKey] = authResponse.toJson()

        }

    }

    fun getData(): Flow<AuthResponse> {
        val datastoreKey = stringPreferencesKey(AUTH_KEY)
        return dataStore.data.map { pref ->
            (if (pref[datastoreKey] == null){
                AuthResponse(token = "")
            }else{
                pref[datastoreKey]?.let { AuthResponse.fromJson(it) }
            })!!
        }
    }
}