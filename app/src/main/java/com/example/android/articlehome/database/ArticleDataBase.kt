package com.example.android.articlehome.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.articlehome.models.ArticleModel

@Database(entities = [ArticleModel::class], version = 1)
abstract class ArticleDataBase: RoomDatabase() {
    abstract val articleDao: ArticleDao
}

private lateinit var INSTANCE:ArticleDataBase

fun getDataBase(context: Context):ArticleDataBase {
    synchronized(ArticleDataBase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ArticleDataBase::class.java,
                "article_database"
            ).build()

        }
    }
    return INSTANCE
}