package com.ayodev.store_challenge.di

import com.ayodev.store_challenge.core.domain.use_case.AuthInteractor
import com.ayodev.store_challenge.core.domain.use_case.AuthUseCase
import com.ayodev.store_challenge.core.domain.use_case.StoreInteractor
import com.ayodev.store_challenge.core.domain.use_case.StoreUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideAuthUseCase(authInteractor: AuthInteractor): AuthUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideStoreUseCase(storeInteractor: StoreInteractor): StoreUseCase
}