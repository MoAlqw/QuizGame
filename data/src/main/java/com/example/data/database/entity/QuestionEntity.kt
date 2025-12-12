package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "questions",
    foreignKeys = [
        ForeignKey(
            entity = TopicEntity::class,
            parentColumns = ["id"],
            childColumns = ["topicId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("topicId")]
)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val question: String,
    val answers: List<String>,
    val correctAnswer: Int,
    val topicId: Int
)
