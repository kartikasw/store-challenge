package com.ayodev.store_challenge.presentation.fragment.main_menu

import androidx.lifecycle.ViewModel
import com.ayodev.store_challenge.core.domain.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {
    fun logout() = authUseCase.logout()
}