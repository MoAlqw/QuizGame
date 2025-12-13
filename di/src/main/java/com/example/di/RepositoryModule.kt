package com.example.di

import com.example.data.repository.QuestionRepositoryImpl
import com.example.data.repository.TopicRepositoryImpl
import com.example.domain.repository.QuestionRepository
import com.example.domain.repository.TopicRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
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
}