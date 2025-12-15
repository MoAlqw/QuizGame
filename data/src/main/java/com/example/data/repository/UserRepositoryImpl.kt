package com.example.data.repository

import com.example.core.model.User
import com.example.data.database.dao.UserDao
import com.example.data.database.entity.UserEntity
import com.example.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun register(user: User) {
        val entity = UserEntity(email = user.email, password = user.password)
        userDao.insert(entity)
    }

    override suspend fun login(email: String, password: String): User? {
        val entity = userDao.login(email, password) ?: return null
        return User(id = entity.id, email = entity.email, password = entity.password)
    }
}