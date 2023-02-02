package com.ayodev.store_challenge.presentation.fragment.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ayodev.store_challenge.core.domain.use_case.StoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase
): ViewModel() {
    fun getAllStore() =
        storeUseCase.getAllStore().asLiveData()
}