package com.lathavel.newsapp.domain.repository

import android.util.Log
import com.lathavel.newsapp.BuildConfig
import com.lathavel.newsapp.data.mapper.toDomainArticleList
import com.lathavel.newsapp.data.mapper.toDomainNewsSourceList
import com.lathavel.newsapp.data.remote.api.NewsApiService
import com.lathavel.newsapp.domain.model.Article
import com.lathavel.newsapp.domain.model.NewsSource
import com.lathavel.newsapp.util.Resource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService
): NewsRepository{

    override suspend fun getTopHeadLines(countryCode: String): Resource<List<Article>> {

        return try{
            val response = apiService.getTopHeadlines(country = "us", apiKey = BuildConfig.NEWS_API_KEY)
            if(response.isSuccessful){
                Log.e("NewsRepositoryImpl", "getTopHeadlines  isSuccessful CALLED")
                val articleSto = response.body()?.articles ?: emptyList()
                Resource.Success(data = articleSto.toDomainArticleList() )
            }else{
                Log.e("NewsRepositoryImpl", "getTopHeadlines  isSuccessful  ELSE CALLED")
                Resource.Error(message = "Error: ${response.code()} + ${response.message()}")
            }
        }catch (e: Exception){
            Log.e("NewsRepositoryImpl", "getTopHeadlines  Exception " + e.printStackTrace())
            Resource.Error(message = e.message.toString())
        }
    }

    override suspend fun getNewsSources(): Resource<List<NewsSource>> {
         return try {
             val response = apiService.getNewsSources(apiKey = BuildConfig.NEWS_API_KEY)
             if(response.isSuccessful){
                 val data = response.body()?.sources?.toDomainNewsSourceList() ?: emptyList()
                 Resource.Success(data)
             }else{
                 Resource.Error(message = "Error ${response.code()} + ${response.message()} ")
             }
         }catch (e: Exception){
             Resource.Error(message = e.message)
         }
    }
}