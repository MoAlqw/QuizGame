package com.example.quiz.model

import com.example.core.model.Topic
import com.example.quiz.R

data class TopicUi(
    val id: Int,
    val name: String,
    val questionsCount: Int,
    val iconRes: Int
)

fun Topic.toUi(): TopicUi = TopicUi(
    id = id,
    name = name,
    questionsCount = 15,
    iconRes = when (name.trim()) {
        "Модели OSI" -> R.drawable.osi
        "TCP/IP" -> R.drawable.tcp_ip
        "Протоколы маршрутов" -> R.drawable.protocols
        else -> R.drawable.ic_launcher_foreground
    }
)