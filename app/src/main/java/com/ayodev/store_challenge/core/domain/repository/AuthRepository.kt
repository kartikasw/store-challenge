package com.ayodev.store_challenge.core.domain.repository

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Login
import com.ayodev.store_challenge.core.domain.model.LoginInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginInfo: LoginInfo): Flow<Resource<Login>>
}