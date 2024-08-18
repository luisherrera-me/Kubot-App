package com.kuby.kubot.di

import com.kuby.kubot.data.datastore.AuthDatastore
import com.kuby.kubot.data.repository.dataSource.AuthLocalDataSource
import com.kuby.kubot.data.repository.dataSourceImpl.AuthLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    fun provideAuthLocalDataSource(
        authDatastore: AuthDatastore
    ): AuthLocalDataSource = AuthLocalDataSourceImpl(authDatastore)

}