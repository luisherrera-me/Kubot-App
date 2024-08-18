package com.kuby.kubot.domain.useCase.auth

import com.kuby.kubot.domain.model.AuthResponse
import com.kuby.kubot.domain.repository.AuthRepository

class SaveSessionUseCase (private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        authResponse: AuthResponse
    ) = authRepository.saveSession(authResponse)
}