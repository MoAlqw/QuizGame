package com.example.domain.repository

import com.example.core.model.Question

interface QuestionRepository {
    suspend fun getQuestionsForTopic(topicId: Int): List<Question>
}