package com.ayodev.store_challenge.core.data.source.remote.response

data class LoginResponse(
    val stores: List<StoreResponse> = listOf(),
    val status: String = "",
    val message: String = ""
)