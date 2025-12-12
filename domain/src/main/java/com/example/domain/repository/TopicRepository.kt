package com.example.domain.repository

import com.example.core.model.Topic

interface TopicRepository {
    suspend fun getTopics(): List<Topic>
}