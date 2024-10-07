package com.kuby.core.InternetConnectivityObserver

import kotlinx.coroutines.flow.Flow

interface IInternetConnectivityObserver {

    val onlineStateFlow: Flow<OnlineStatus>

    fun connectivityFlow(): Flow<ConnectivityStatus>

    enum class OnlineStatus {
        ONLINE,
        OFFLINE
    }

    enum class InternetReachabilityStatus {
        REACHABLE,
        UNREACHABLE,
    }

    enum class ConnectivityStatus {
        Available, Unavailable, Losing, Lost
    }
}