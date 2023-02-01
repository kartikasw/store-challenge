package com.ayodev.store_challenge.core.domain.use_case

import com.ayodev.store_challenge.core.domain.repository.StoreRepository
import javax.inject.Inject

class StoreInteractor @Inject constructor(
    private val storeRepository: StoreRepository
    ) : StoreUseCase {

}