package com.example.quiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Topic
import com.example.domain.usecase.topic.GetTopicsUseCase
import com.example.quiz.model.TopicFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.filter

@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val getTopicsUseCase: GetTopicsUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    private val _filter = MutableStateFlow(TopicFilter())

    @OptIn(FlowPreview::class)
    val filteredTopics: StateFlow<List<Topic>> = combine(
        _topics,
        _searchQuery.debounce(300),
        _filter
    ) { topics, query, filter ->
        var result = topics

        if (query.isNotBlank()) {
            result = result.filter { it.name.contains(query, ignoreCase = true) }
        }

        filter.topicId?.let { id ->
            result = result.filter { it.id == id }
        }

        result
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    init {
        viewModelScope.launch {
            getTopicsUseCase().collect { _topics.value = it }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun applyTopicFilter(topicId: Int) {
        _filter.value = TopicFilter(topicId)
    }

    fun resetFilter() {
        _filter.value = TopicFilter()
    }
}