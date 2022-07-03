package com.example.android.articlehome.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.articlehome.models.ArticleModel

@Database(entities = [ArticleModel::class], version = 1, exportSchema = false)
abstract class ArticleDataBase: RoomDatabase() {

    abstract val articleDao: ArticleDao

    companion object {

        @Volatile
        private var INSTANCE: ArticleDataBase? = null

        fun getDatabase(context: Context): ArticleDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            ArticleDataBase::class.java,
                            "article_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}