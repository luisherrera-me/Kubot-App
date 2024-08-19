package com.kuby.kubot.di

import com.kuby.kubot.data.remote.AuthService
import com.kuby.kubot.data.repository.dataSource.AuthRemoteDataSource
import com.kuby.kubot.data.repository.dataSourceImpl.AuthRemoteDataSourceImpl
import com.kuby.kubot.domain.repository.AuthRepository
import com.kuby.kubot.domain.useCase.auth.AuthUseCase
import com.kuby.kubot.domain.useCase.auth.DeleteSessionUseCase
import com.kuby.kubot.domain.useCase.auth.GetSessionDataUseCase
import com.kuby.kubot.domain.useCase.auth.LoginUseCase
import com.kuby.kubot.domain.useCase.auth.RegisterUseCase
import com.kuby.kubot.domain.useCase.auth.SaveSessionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository) = AuthUseCase(
        login = LoginUseCase(authRepository),
        register = RegisterUseCase(authRepository),
        saveSession = SaveSessionUseCase(authRepository),
        getSessionData = GetSessionDataUseCase(authRepository),
        deleteSession = DeleteSessionUseCase(authRepository)

    )
}