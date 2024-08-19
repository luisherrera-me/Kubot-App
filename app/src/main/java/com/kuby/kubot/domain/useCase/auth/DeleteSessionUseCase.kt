package com.kuby.kubot.domain.useCase.auth

import com.kuby.kubot.domain.model.AuthResponse
import com.kuby.kubot.domain.repository.AuthRepository

class DeleteSessionUseCase (private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.deleteSession()
}