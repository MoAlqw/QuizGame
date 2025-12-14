package com.example.data.repository

import com.example.core.model.Question
import com.example.data.database.dao.QuestionDao
import com.example.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepositoryImpl @Inject constructor(
    private val questionDao: QuestionDao
): QuestionRepository {

    override fun getQuestionsForTopic(topicId: Int): Flow<List<Question>> {
        return questionDao
            .getQuestionsForTopic(topicId)
            .map { entities ->
                entities.map { entity ->
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

    override fun getQuestionsCountForTopic(topicId: Int): Flow<Int> {
        return questionDao.getQuestionsCountForTopic(topicId)
    }
}
