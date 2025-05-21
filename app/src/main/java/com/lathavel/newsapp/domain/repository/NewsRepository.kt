package com.lathavel.newsapp.domain.repository

import com.lathavel.newsapp.domain.model.Article
import com.lathavel.newsapp.domain.model.NewsSource
import com.lathavel.newsapp.util.Resource

interface NewsRepository {
    suspend fun getTopHeadLines(countryCode:String): Resource<List<Article>>

    suspend fun getNewsSources(): Resource<List<NewsSource>>
}