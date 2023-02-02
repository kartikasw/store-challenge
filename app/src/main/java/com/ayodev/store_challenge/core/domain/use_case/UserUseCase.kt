package com.ayodev.store_challenge.core.domain.use_case

interface UserUseCase {
    fun getLoginStatus(): Boolean
}