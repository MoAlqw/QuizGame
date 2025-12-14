package com.example.data.repository

import com.example.core.model.Topic
import com.example.data.database.dao.TopicDao
import com.example.domain.repository.TopicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopicRepositoryImpl @Inject constructor(
    private val topicDao: TopicDao
): TopicRepository {

    override fun getTopics(): Flow<List<Topic>> {
        return topicDao.getTopics().map { entities ->
            entities.map { entity ->
                Topic(
                    entity.id,
                    entity.name
                )
            }
        }
    }
}