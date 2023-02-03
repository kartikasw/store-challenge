package com.ayodev.store_challenge.presentation.activity.maps

import android.location.Location
import android.util.Log
import androidx.lifecycle.*
import com.ayodev.store_challenge.core.domain.use_case.StoreUseCase
import com.ayodev.store_challenge.core.domain.use_case.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase,
    private val userUseCase: UserUseCase
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        _loading.value = true
    }

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    fun getAllStore() =
        storeUseCase.getAllStore().asLiveData()

    fun getCurrentLocation() {
        viewModelScope.launch {
            _loading.value = true
            val userLocation = userUseCase.getCurrentLocation()
            if(userLocation != null) {
                _location.value = userLocation!!
                Log.d("ViewModel", "Location: $userLocation")
                _loading.value = false
            }
        }
    }
}