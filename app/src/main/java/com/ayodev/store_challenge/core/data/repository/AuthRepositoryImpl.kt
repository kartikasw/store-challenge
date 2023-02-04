package com.ayodev.store_challenge.core.data.repository

import com.ayodev.store_challenge.core.data.Response
import com.ayodev.store_challenge.core.data.mapper.toListEntity
import com.ayodev.store_challenge.core.data.mapper.toModel
import com.ayodev.store_challenge.core.data.source.local.LocalDataSource
import com.ayodev.store_challenge.core.data.source.remote.RemoteDataSource
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Login
import com.ayodev.store_challenge.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
): AuthRepository {
    override fun getUsername(): String? =
        local.getUsername()

    override suspend fun login(save: Boolean, username: String, password: String): Flow<Resource<Login>> =
        flow {
            emit(Resource.Loading())
            when(val response = remote.login(username, password).first()) {
                is Response.Success -> {
                    if (!local.getLoginStatus()) {
                        local.insertAllStore(response.data.stores.toListEntity())
                    }
                    local.setLoginStatus(true)
                    if(save) { local.setUsername(username) }
                    emit(Resource.Success(response.data.toModel()))
                }
                is Response.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
                else -> {}
            }
        }

    override fun logout() {
        local.setLoginStatus(false)
    }

}