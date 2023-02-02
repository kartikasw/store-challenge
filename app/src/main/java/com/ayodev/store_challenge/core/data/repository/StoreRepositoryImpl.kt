package com.ayodev.store_challenge.core.data.repository

import com.ayodev.store_challenge.core.data.mapper.toListFlowModel
import com.ayodev.store_challenge.core.data.source.local.LocalDataSource
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.core.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
): StoreRepository {
    override fun getAllStore(): Flow<Resource<List<Store>>> =
        local.selectAllStore().toListFlowModel().map {
            Resource.Success(it)
        }
}