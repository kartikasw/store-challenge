package com.ayodev.store_challenge.core.data.repository

import android.location.Location
import com.ayodev.store_challenge.core.data.source.local.LocalDataSource
import com.ayodev.store_challenge.core.data.source.remote.RemoteDataSource
import com.ayodev.store_challenge.core.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
): UserRepository {
    override fun getLoginStatus(): Boolean =
        local.getLoginStatus()

    override suspend fun getCurrentLocation(): Location? =
        remote.getCurrentLocation()
}