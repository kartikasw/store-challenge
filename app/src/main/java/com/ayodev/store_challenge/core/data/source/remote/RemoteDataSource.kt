package com.ayodev.store_challenge.core.data.source.remote

import com.ayodev.store_challenge.core.data.Response
import com.ayodev.store_challenge.core.data.source.remote.response.LoginResponse
import com.ayodev.store_challenge.core.data.source.remote.service.ApiService
import com.ayodev.store_challenge.core.data.source.remote.service.LocationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val api: ApiService,
    private val location: LocationApi,
) {
    suspend fun login(username: String, password: String): Flow<Response<LoginResponse>> =
        flow {
            val response = api.login(username, password)
            if(response.status == "success") {
                emit(Response.Success(response))
            } else {
                emit(Response.Error(response.message))
            }
        }.catch {
            emit(Response.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    suspend fun getCurrentLocation() = location.getCurrentLocation()
}