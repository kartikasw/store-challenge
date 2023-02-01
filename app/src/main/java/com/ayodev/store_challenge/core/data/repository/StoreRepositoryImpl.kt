package com.ayodev.store_challenge.core.data.repository

import com.ayodev.store_challenge.core.data.source.local.LocalDataSource
import com.ayodev.store_challenge.core.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
): StoreRepository {
}