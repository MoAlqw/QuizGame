package com.example.domain.usecase.topic

import com.example.core.model.Topic
import com.example.domain.repository.TopicRepository
import kotlinx.coroutines.flow.Flow

class GetTopicsUseCase(
    private val topicRepository: TopicRepository
) {
    operator fun invoke(): Flow<List<Topic>> {
        return topicRepository.getTopics()
    }
}