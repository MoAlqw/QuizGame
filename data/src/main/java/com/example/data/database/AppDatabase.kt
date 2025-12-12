package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.database.converters.ListConverter
import com.example.data.database.dao.QuestionDao
import com.example.data.database.dao.TopicDao
import com.example.data.database.entity.QuestionEntity
import com.example.data.database.entity.TopicEntity
import com.example.data.util.JsonLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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