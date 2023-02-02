package com.ayodev.store_challenge.presentation.fragment.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ayodev.store_challenge.core.domain.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {
    suspend fun login(username: String, password: String) =
        authUseCase.login(username, password).asLiveData()
}