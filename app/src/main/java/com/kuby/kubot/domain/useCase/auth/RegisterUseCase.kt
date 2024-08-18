package com.kuby.kubot.domain.useCase.auth

import com.kuby.kubot.domain.model.LoginResponse
import com.kuby.kubot.domain.model.User
import com.kuby.kubot.domain.repository.AuthRepository

class RegisterUseCase (private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: User) = authRepository.register(user)
}