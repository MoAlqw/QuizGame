package com.example.domain.usecase.user

import com.example.core.model.User
import com.example.domain.repository.UserRepository

class LoginUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): User? {
        return userRepository.login(email, password)
    }
}