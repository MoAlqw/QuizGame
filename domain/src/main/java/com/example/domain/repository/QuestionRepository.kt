package com.example.domain.repository

import com.example.core.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestionsForTopic(topicId: Int): Flow<List<Question>>
    fun getQuestionsCountForTopic(topicId: Int): Flow<Int>
}