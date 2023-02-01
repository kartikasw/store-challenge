package com.ayodev.store_challenge.core.data.source.remote.service

import com.ayodev.store_challenge.core.data.source.remote.response.LoginResponse
import com.ayodev.store_challenge.core.domain.model.LoginInfo
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login/loginTest")
    suspend fun login(
        @Body loginInfo: LoginInfo
    ): LoginResponse
}