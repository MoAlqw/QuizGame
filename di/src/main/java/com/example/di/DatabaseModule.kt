package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.AppDatabase
import com.example.data.database.dao.QuestionDao
import com.example.data.database.dao.TopicDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "trainer.db"
        )
            .createFromAsset("trainer.db")
            .build()
    }

    @Provides
    fun provideTopicDao(db: AppDatabase): TopicDao = db.topicDao()

    @Provides
    fun provideQuestionDao(db: AppDatabase): QuestionDao = db.questionDao()
}