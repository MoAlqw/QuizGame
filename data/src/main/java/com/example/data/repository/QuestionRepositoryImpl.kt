package com.example.data.repository

import com.example.core.model.Question
import com.example.data.database.dao.QuestionDao
import com.example.domain.repository.QuestionRepository

class QuestionRepositoryImpl(private val questionDao: QuestionDao): QuestionRepository {

    override suspend fun getQuestionsForTopic(topicId: Int): List<Question> {
        return questionDao.getQuestionsForTopic(topicId).map { entity ->
            Question(
                id = entity.id,
                question = entity.question,
                answers = entity.answers,
                correctAnswer = entity.correctAnswer,
                topic = entity.topicId
            )
        }
    }
}
