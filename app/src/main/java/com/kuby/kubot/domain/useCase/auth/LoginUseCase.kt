package com.kuby.kubot.domain.useCase.auth

import com.kuby.kubot.domain.model.LoginResponse
import com.kuby.kubot.domain.repository.AuthRepository

class LoginUseCase (private val authRepository: AuthRepository) {
    suspend operator fun invoke(loginResponse: LoginResponse) = authRepository.login(loginResponse)


}