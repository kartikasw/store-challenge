package com.ayodev.store_challenge.core.domain.repository

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    fun getAllStore(): Flow<Resource<List<Store>>>
}