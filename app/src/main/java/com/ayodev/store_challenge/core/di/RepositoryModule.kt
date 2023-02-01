package com.ayodev.store_challenge.core.di

import com.ayodev.store_challenge.core.data.repository.AuthRepositoryImpl
import com.ayodev.store_challenge.core.data.repository.StoreRepositoryImpl
import com.ayodev.store_challenge.core.domain.repository.AuthRepository
import com.ayodev.store_challenge.core.domain.repository.StoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun provideStoreRepository(storeRepositoryImpl: StoreRepositoryImpl): StoreRepository
}