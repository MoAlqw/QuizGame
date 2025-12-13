package com.example.domain.usecase.topic

import com.example.core.model.Topic
import com.example.domain.repository.TopicRepository
import javax.inject.Inject

class GetTopicsUseCase @Inject constructor(
    private val topicRepository: TopicRepository
) {
    suspend operator fun invoke(): List<Topic> {
        return topicRepository.getTopics()
    }
}