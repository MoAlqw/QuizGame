package com.example.data.repository

import com.example.core.model.Topic
import com.example.data.database.dao.TopicDao
import com.example.domain.repository.TopicRepository

class TopicRepositoryImpl(private val topicDao: TopicDao): TopicRepository {

    override suspend fun getTopics(): List<Topic> {
        return topicDao.getTopics().map { entity ->
            Topic(
                entity.id,
                entity.name
            )
        }
    }
}