package com.ayodev.store_challenge.core.data.repository

import com.ayodev.store_challenge.core.data.Response
import com.ayodev.store_challenge.core.data.mapper.toModel
import com.ayodev.store_challenge.core.data.source.remote.RemoteDataSource
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Login
import com.ayodev.store_challenge.core.domain.model.LoginInfo
import com.ayodev.store_challenge.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource
): AuthRepository {
    override suspend fun login(loginInfo: LoginInfo): Flow<Resource<Login>> =
        flow {
            emit(Resource.Loading())
            when(val response = remote.login(loginInfo).first()) {
                is Response.Success -> {
                    emit(Resource.Success(response.data.toModel()))
                }
                is Response.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
                else -> {}
            }
        }

}