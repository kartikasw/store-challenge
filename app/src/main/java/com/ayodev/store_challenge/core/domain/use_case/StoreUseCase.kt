package com.ayodev.store_challenge.core.domain.use_case

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import kotlinx.coroutines.flow.Flow

interface StoreUseCase {
    fun getAllStore(): Flow<Resource<List<Store>>>
}