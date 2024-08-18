package com.kuby.kubot.di

import com.kuby.kubot.data.remote.AuthService
import com.kuby.kubot.data.repository.dataSource.AuthRemoteDataSource
import com.kuby.kubot.data.repository.dataSourceImpl.AuthRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    fun provideAuthRemoteDataSource(
        authService: AuthService
    ): AuthRemoteDataSource = AuthRemoteDataSourceImpl(authService)

}
