package com.ayodev.store_challenge.presentation.splash

import androidx.lifecycle.ViewModel
import com.ayodev.store_challenge.core.domain.use_case.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {
    fun getLoginStatus() = userUseCase.getLoginStatus()
}