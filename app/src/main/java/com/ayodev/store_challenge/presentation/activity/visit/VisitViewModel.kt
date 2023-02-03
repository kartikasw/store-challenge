package com.ayodev.store_challenge.presentation.activity.visit

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayodev.store_challenge.core.domain.use_case.StoreUseCase
import com.ayodev.store_challenge.core.domain.use_case.UserUseCase
import com.ayodev.store_challenge.util.distanceInKm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class VisitViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase,
    private val userUseCase: UserUseCase
): ViewModel() {

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> = _bitmap

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _locationState = MutableLiveData<Boolean>()
    val locationState: LiveData<Boolean> = _locationState

    private val _distance = MutableLiveData<Double>()
    val distance: LiveData<Double> = _distance

    init {
        _locationState.value = false
    }

    fun updateBitmap(bitmap: Bitmap) {
        _bitmap.value = bitmap
    }

    fun updateLocationState(state: Boolean) {
        _locationState.value = state
    }

    fun updateStoreWhenVisit(id: Int, visit: Boolean, visit_date: Date, image: Bitmap) =
        viewModelScope.launch {
            storeUseCase.updateStoreWhenVisit(id, visit, visit_date, image)
        }

    fun getCurrentLocation(lat: Double, lon: Double) {
        viewModelScope.launch {
            _loading.value = true
            val location = userUseCase.getCurrentLocation()
            if(location != null) {
                val distance = distanceInKm(location!!.latitude, location.longitude, lat, lon)
                _distance.value = distance
                Log.d("ViewModel", "User's location: $location || $distance and ${distance < 0.1}")
                _locationState.value = distance < 0.1
            }
            _loading.value = false
        }
    }
}