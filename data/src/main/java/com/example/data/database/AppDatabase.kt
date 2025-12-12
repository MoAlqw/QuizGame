package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.converters.ListConverter
import com.example.data.database.dao.QuestionDao
import com.example.data.database.dao.TopicDao
import com.example.data.database.entity.QuestionEntity
import com.example.data.database.entity.TopicEntity

@Database(
    entities = [
        TopicEntity::class,
        QuestionEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun topicDao(): TopicDao
    abstract fun questionDao(): QuestionDao
}