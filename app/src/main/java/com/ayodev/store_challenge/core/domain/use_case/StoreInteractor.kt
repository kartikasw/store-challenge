package com.ayodev.store_challenge.core.domain.use_case

import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.core.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreInteractor @Inject constructor(
    private val storeRepository: StoreRepository
    ) : StoreUseCase {
    override fun getAllStore(): Flow<Resource<List<Store>>> =
        storeRepository.getAllStore()

}