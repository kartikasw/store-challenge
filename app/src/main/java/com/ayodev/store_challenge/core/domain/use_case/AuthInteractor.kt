package com.ayodev.store_challenge.core.domain.use_case

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Login
import com.ayodev.store_challenge.core.domain.model.LoginInfo
import com.ayodev.store_challenge.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: AuthRepository): AuthUseCase {
    override suspend fun login(loginInfo: LoginInfo): Flow<Resource<Login>> =
        authRepository.login(loginInfo)

}