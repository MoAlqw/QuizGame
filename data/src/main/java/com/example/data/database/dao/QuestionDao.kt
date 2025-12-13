package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entity.QuestionEntity

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE topicId = :topicId")
    suspend fun getQuestionsForTopic(topicId: Int): List<QuestionEntity>

    @Query("SELECT COUNT(*) FROM questions WHERE topicId = :topicId")
    suspend fun getQuestionsCountForTopic(topicId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionEntity>)
}