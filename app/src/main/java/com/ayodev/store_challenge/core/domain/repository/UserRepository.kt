package com.ayodev.store_challenge.core.domain.repository

interface UserRepository {
    fun getLoginStatus(): Boolean
}