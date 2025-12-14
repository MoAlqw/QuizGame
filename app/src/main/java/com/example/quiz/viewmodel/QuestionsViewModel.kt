package com.example.quiz.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Question
import com.example.domain.usecase.question.GetQuestionsForTopicUseCase
import com.example.quiz.screens.fragment.QuestionsFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getQuestionsForTopicUseCase: GetQuestionsForTopicUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val topicId: Int = savedStateHandle[QuestionsFragment.TOPIC_ID] ?: 0

    private val _questions = MutableStateFlow<List<Question>>(emptyList())

    private val _currentIndex = MutableStateFlow(0)



    private var _score = 0
    val score get() = _score

    val currentQuestion: StateFlow<Question?> = combine(_questions, _currentIndex) { list, index ->
        list.getOrNull(index)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    init {
        viewModelScope.launch {
            getQuestionsForTopicUseCase(topicId)
                .collect { questionList ->
                    _questions.value = questionList
                    _currentIndex.value = 0
                }
        }
    }

    fun moveToNextQuestion(selectedPosition: Int?) {
        if (selectedPosition == -1 || selectedPosition == null) return
        checkAnswer(selectedPosition)
        val next = _currentIndex.value + 1
        if (next < _questions.value.size) {
            _currentIndex.value = next
        }
    }

    private fun checkAnswer(selectedPosition: Int) {
        if (selectedPosition == currentQuestion.value!!.correctAnswer) _score++
    }

    fun hasNext(): Boolean = _currentIndex.value < _questions.value.size - 1
}