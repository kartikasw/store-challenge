package com.ayodev.store_challenge.core.data.mapper

import com.ayodev.store_challenge.core.data.source.remote.response.LoginResponse
import com.ayodev.store_challenge.core.domain.model.Login

fun LoginResponse.toModel(): Login = Login(status, message)
