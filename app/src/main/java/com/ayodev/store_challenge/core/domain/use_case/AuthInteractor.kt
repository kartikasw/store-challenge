package com.ayodev.store_challenge.core.domain.use_case

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Login
import com.ayodev.store_challenge.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: AuthRepository): AuthUseCase {
    override fun getUsername(): String? =
        authRepository.getUsername()

    override suspend fun login(save: Boolean, username: String, password: String): Flow<Resource<Login>> =
        authRepository.login(save, username, password)

    override fun logout() = authRepository.logout()
}