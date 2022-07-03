package com.example.android.articlehome.repository

import androidx.lifecycle.LiveData
import com.example.android.articlehome.database.ArticleDataBase
import com.example.android.articlehome.models.ArticleModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ArticleRepository (private val asteroidDataBase:ArticleDataBase){

    var allSavedArticles: LiveData<List<ArticleModel>> =asteroidDataBase.articleDao.getAllArticles()
    suspend fun insertArticle(article:ArticleModel){
        withContext(Dispatchers.IO){
            asteroidDataBase.articleDao.insertArticle(article)
        }
    }
}