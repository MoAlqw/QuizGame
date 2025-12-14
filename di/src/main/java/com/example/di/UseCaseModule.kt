package com.example.di

import com.example.domain.repository.QuestionRepository
import com.example.domain.repository.TopicRepository
import com.example.domain.usecase.question.GetQuestionsForTopicUseCase
import com.example.domain.usecase.topic.GetTopicsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetTopicsUseCase(topicRepository: TopicRepository): GetTopicsUseCase {
        return GetTopicsUseCase(topicRepository)
    }

    @Provides
    fun provideGetQuestionsForTopicUseCase(questionRepository: QuestionRepository): GetQuestionsForTopicUseCase {
        return GetQuestionsForTopicUseCase(questionRepository)
    }
}