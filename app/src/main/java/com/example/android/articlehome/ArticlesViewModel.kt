package com.example.android.articlehome

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.articlehome.api.Network
import com.example.android.articlehome.models.ArticleModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber

class ArticlesViewModel(application: Application) : AndroidViewModel(application){
    private var _articles = MutableLiveData<List<ArticleModel>>()
    val articles: LiveData<List<Asteroid>>
        get() = _articles
    init {
        viewModelScope.launch {
            refreshArticles()
        }
    }

    private suspend fun refreshArticles(){
            Network.service.getArticlesAsync("edaca808-eed7-450f-9860-0374e7fc2e49").await().let {
            val result = JSONObject(it.string()).getJSONObject("response").getJSONArray("results").toString()
            val articles: List<ArticleModel> =
                Gson().fromJson(result, object : TypeToken<List<ArticleModel?>?>() {}.type)
            Timber.v(articles[0].id)
        }
    }
}