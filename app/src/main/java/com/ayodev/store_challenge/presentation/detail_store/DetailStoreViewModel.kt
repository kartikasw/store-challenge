package com.ayodev.store_challenge.presentation.detail_store

import androidx.lifecycle.ViewModel
import com.ayodev.store_challenge.core.domain.use_case.StoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailStoreViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase
): ViewModel() {
    suspend fun getStoreById(id: Int) =
        storeUseCase.getStoreById(id)
}