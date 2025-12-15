package com.example.domain.repository

import com.example.core.model.User

interface UserRepository {
    suspend fun register(user: User)
    suspend fun login(email: String, password: String): User?
}