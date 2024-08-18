package com.kuby.kubot.di

import com.kuby.kubot.data.remote.AuthService
import com.kuby.kubot.data.repository.AuthRepositoryImpl
import com.kuby.kubot.data.repository.RepositoryImpl
import com.kuby.kubot.data.repository.dataSource.AuthLocalDataSource
import com.kuby.kubot.data.repository.dataSource.AuthRemoteDataSource
import com.kuby.kubot.domain.repository.AuthRepository
import com.kuby.kubot.domain.repository.DataStoreOperations
import com.kuby.kubot.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        dataStoreOperations: DataStoreOperations,
        ktorApi: AuthService
    ): Repository {
        return RepositoryImpl(
            dataStoreOperations = dataStoreOperations,
            ktorApi = ktorApi
        )
    }


    @Provides
    @Singleton
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        authLocalDataSource: AuthLocalDataSource
    ): AuthRepository = AuthRepositoryImpl(
        authRemoteDataSource,
        authLocalDataSource
    )



}