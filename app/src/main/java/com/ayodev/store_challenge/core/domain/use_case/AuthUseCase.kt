package com.ayodev.store_challenge.core.domain.use_case

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Login
import com.ayodev.store_challenge.core.domain.model.LoginInfo
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun login(loginInfo: LoginInfo): Flow<Resource<Login>>
}