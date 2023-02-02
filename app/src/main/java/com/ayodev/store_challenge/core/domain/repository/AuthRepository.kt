package com.ayodev.store_challenge.core.domain.repository

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Login
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<Resource<Login>>
    fun logout()
}