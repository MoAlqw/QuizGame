package com.example.domain.usecase.question

import com.example.core.model.Question
import com.example.domain.repository.QuestionRepository
import javax.inject.Inject

class GetQuestionsForTopicUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(topicId: Int): List<Question> {
        return questionRepository.getQuestionsForTopic(topicId)
    }
}