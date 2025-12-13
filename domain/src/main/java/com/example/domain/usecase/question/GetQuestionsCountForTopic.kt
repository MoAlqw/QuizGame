package com.example.domain.usecase.question

import com.example.domain.repository.QuestionRepository
import javax.inject.Inject

class GetQuestionsCountForTopic @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(topicId: Int): Int {
        return questionRepository.getQuestionsCountForTopic(topicId)
    }
}