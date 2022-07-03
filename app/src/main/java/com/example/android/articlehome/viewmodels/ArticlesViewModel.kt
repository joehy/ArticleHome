package com.example.android.articlehome.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.articlehome.api.Network
import com.example.android.articlehome.database.ArticleDataBase.Companion.getDatabase
import com.example.android.articlehome.models.ArticleModel
import com.example.android.articlehome.repository.ArticleRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.json.JSONObject

class ArticlesViewModel(application: Application) : AndroidViewModel(application){
    private val database = getDatabase(application)
    private val appRepository=ArticleRepository(database)

    val allSavedArticles = appRepository.allSavedArticles
    private var _articles = MutableLiveData<List<ArticleModel>>()
    val articles: LiveData<List<ArticleModel>>
        get() = _articles
    init {
        viewModelScope.launch {
            refreshArticles()
        }
    }

    private suspend fun refreshArticles(){
            Network.service.getArticlesAsync("edaca808-eed7-450f-9860-0374e7fc2e49").await().let {
            val result = JSONObject(it.string()).getJSONObject("response").getJSONArray("results").toString()
            val articlesList: List<ArticleModel> =
                Gson().fromJson(result, object : TypeToken<List<ArticleModel?>?>() {}.type)
            _articles.value=articlesList
        }
    }
}