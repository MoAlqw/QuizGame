package com.example.domain.usecase.user

import com.example.core.model.User
import com.example.domain.repository.UserRepository

class RegisterUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.register(user)
    }
}