package com.ayodev.store_challenge.core.domain.use_case

import android.location.Location

interface UserUseCase {
    fun getLoginStatus(): Boolean
    suspend fun getCurrentLocation(): Location?
}