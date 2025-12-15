package com.example.di

import com.example.domain.repository.QuestionRepository
import com.example.domain.repository.TopicRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.question.GetQuestionsForTopicUseCase
import com.example.domain.usecase.topic.GetTopicsUseCase
import com.example.domain.usecase.user.LoginUserUseCase
import com.example.domain.usecase.user.RegisterUserUseCase
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

    @Provides
    fun provideRegisterUserUseCase(userRepository: UserRepository): RegisterUserUseCase {
        return RegisterUserUseCase(userRepository)
    }

    @Provides
    fun provideLoginUserUseCase(userRepository: UserRepository): LoginUserUseCase {
        return LoginUserUseCase(userRepository)
    }
}