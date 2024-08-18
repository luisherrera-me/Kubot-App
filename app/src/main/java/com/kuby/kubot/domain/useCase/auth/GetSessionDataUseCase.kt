package com.kuby.kubot.domain.useCase.auth

import com.kuby.kubot.domain.repository.AuthRepository

class GetSessionDataUseCase(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.getSessionData()
}