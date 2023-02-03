package com.ayodev.store_challenge.core.domain.use_case

import android.graphics.Bitmap
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.core.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class StoreInteractor @Inject constructor(
    private val storeRepository: StoreRepository
    ) : StoreUseCase {
    override fun getAllStore(): Flow<Resource<List<Store>>> =
        storeRepository.getAllStore()

    override suspend fun updateStoreWhenVisit(id: Int, visit: Boolean, visit_date: Date, image: Bitmap) {
        storeRepository.updateStoreWhenVisit(id, visit, visit_date, image)
    }
}