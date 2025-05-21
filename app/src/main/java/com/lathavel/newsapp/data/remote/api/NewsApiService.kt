package com.lathavel.newsapp.data.remote.api

import com.lathavel.newsapp.data.remote.dto.SourceResponseDto
import com.lathavel.newsapp.data.remote.dto.TopHeadLinesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines/")
    suspend fun getTopHeadlines(
        @Query("country") country:String,
        @Query("apiKey") apiKey: String
    ): Response<TopHeadLinesResponseDto>

    @GET("v2/top-headlines/sources/")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey:String
    ): Response<SourceResponseDto>

}