package com.example.core.model

data class Question(
    val id: Int,
    val question: String,
    val answers: List<String>,
    val correctAnswer: Int,
    val topic: Int
)