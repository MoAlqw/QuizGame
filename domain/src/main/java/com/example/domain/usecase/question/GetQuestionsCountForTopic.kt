package com.example.domain.usecase.question

import com.example.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow

class GetQuestionsCountForTopic(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(topicId: Int): Flow<Int> {
        return questionRepository.getQuestionsCountForTopic(topicId)
    }
}