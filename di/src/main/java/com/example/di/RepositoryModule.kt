package com.example.di

import com.example.data.repository.QuestionRepositoryImpl
import com.example.data.repository.TopicRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.QuestionRepository
import com.example.domain.repository.TopicRepository
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindQuestionRepository(
        impl: QuestionRepositoryImpl
    ): QuestionRepository

    @Binds
    abstract fun bindTopicRepository(
        impl: TopicRepositoryImpl
    ): TopicRepository

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}