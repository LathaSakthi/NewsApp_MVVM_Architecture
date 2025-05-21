package com.lathavel.newsapp.domain.model

data class NewsSource(
    val sourceId: String?,
    val sourceName: String?,
    val description: String?,
    val url: String?,
    val category: String?,
    val language: String?,
    val country: String?
)