package com.example.domain.usecase.question

import com.example.core.model.Question
import com.example.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow

class GetQuestionsForTopicUseCase(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(topicId: Int): Flow<List<Question>> {
        return questionRepository.getQuestionsForTopic(topicId)
    }
}