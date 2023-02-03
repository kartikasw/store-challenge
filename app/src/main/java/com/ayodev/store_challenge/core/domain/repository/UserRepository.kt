package com.ayodev.store_challenge.core.domain.repository

import android.location.Location

interface UserRepository {
    fun getLoginStatus(): Boolean
    suspend fun getCurrentLocation(): Location?
}