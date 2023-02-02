package com.ayodev.store_challenge.core.domain.use_case

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Login
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun login(username: String, password: String): Flow<Resource<Login>>
    fun logout()
}