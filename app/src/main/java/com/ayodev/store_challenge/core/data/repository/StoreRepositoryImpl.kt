package com.ayodev.store_challenge.core.data.repository

import android.graphics.Bitmap
import com.ayodev.store_challenge.core.data.mapper.toListFlowModel
import com.ayodev.store_challenge.core.data.source.local.LocalDataSource
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.core.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
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

    override suspend fun updateStoreWhenVisit(id: Int, visit: Boolean, visit_date: Date, image: Bitmap) {
        local.updateStoreWhenVisit(id, visit, visit_date, image)
    }
}