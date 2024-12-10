package com.example.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.util.query
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class NewsViewModel: ViewModel(){

    private val _articles = MutableLiveData<List<Article>>()
    private val _techarticles = MutableLiveData<List<Article>>()
    val techArticles: LiveData<List<Article>> = _techarticles
    val article:LiveData<List<Article>> = _articles
    init {
        fetchNewsTopHeadline()
        fetchTechNews()
    }
    init {
        fetchLatestNews(
            query = "Technology"
        )
    }

    fun fetchTechNews() {
        val newsApiClient = NewsApiClient(constant.apikey)
        val request = EverythingRequest.Builder()
            .q("technology") // Query for technology-related news
            .language("en")
            .build()

        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _techarticles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                Log.e("TechNewsFetch", throwable?.localizedMessage ?: "Unknown error")
            }
        })
    }


    fun fetchNewsTopHeadline(category: String = "General"){
        val newsApiClient=NewsApiClient(constant.apikey)
        val request= TopHeadlinesRequest.Builder().language("en").category(category).build()
        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback{
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("News Response Failed", throwable.localizedMessage)
                }
            }

        })
    }

    fun fetchLatestNews(query: String) {
        val newsApiClient = NewsApiClient(constant.apikey)
        val request = EverythingRequest.Builder()
            .q(query) // Query term for the latest news
            .language("en")
            .sortBy("publishedAt") // Sort articles by recent publication date
            .build()

        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                throwable?.let {
                    Log.e("News Fetch Error", it.localizedMessage ?: "Unknown error")
                }
            }
        })
    }

    fun findArticleByTitle(title: String?): Article? {
        return _articles.value?.find { it.title == title }
    }


    fun fetchEverythingWithQuery(query: String){
        val newsApiClient=NewsApiClient(constant.apikey)
        val request= EverythingRequest.Builder().language("en").q(query).build()
        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback{
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("News Response Failed", throwable.localizedMessage)
                }
            }

        })
    }

}