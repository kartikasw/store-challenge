package com.ayodev.store_challenge.core.data.source.remote.service

import com.ayodev.store_challenge.core.data.source.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login/loginTest")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse
}