package com.ayodev.store_challenge.core.domain.repository

import android.graphics.Bitmap
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import kotlinx.coroutines.flow.Flow
import java.util.*

interface StoreRepository {
    fun getAllStore(): Flow<Resource<List<Store>>>
    suspend fun getStoreById(id: Int): Store
    suspend fun updateStoreWhenVisit(id: Int, visit: Boolean, visit_date: Date, image: Bitmap)
    suspend fun updateStoreVisit(id: Int, visit: Boolean)
    fun searchStore(search: String):  Flow<Resource<List<Store>>>
}