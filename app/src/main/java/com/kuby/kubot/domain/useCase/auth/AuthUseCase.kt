package com.kuby.kubot.domain.useCase.auth

data class AuthUseCase (
    val login: LoginUseCase,
    val register: RegisterUseCase,
    val saveSession: SaveSessionUseCase,
    val getSessionData: GetSessionDataUseCase,
    val deleteSession: DeleteSessionUseCase



)