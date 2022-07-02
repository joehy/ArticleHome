package com.example.android.articlehome.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.articlehome.models.ArticleModel

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticle(vararg articleModel: ArticleModel)
    @Query("SELECT * FROM article_table")
    fun getAllArticles(): LiveData<List<ArticleModel>>  // No need for a suspend function since LiveData is already asynchronous.

}