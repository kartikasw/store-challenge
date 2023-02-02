package com.ayodev.store_challenge.core.domain.use_case

import com.ayodev.store_challenge.core.domain.repository.UserRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: UserRepository): UserUseCase {
    override fun getLoginStatus(): Boolean =
        userRepository.getLoginStatus()
}