package com.lathavel.newsapp.data.mapper

import com.lathavel.newsapp.data.remote.dto.ArticleDto
import com.lathavel.newsapp.data.remote.dto.SourceDto
import com.lathavel.newsapp.domain.model.Article
import com.squareup.moshi.Json

//convert article dto to domain article
fun ArticleDto.toDomainArticle(): Article {

    return Article(
       sourceName = this.source?.name,
       sourceId = this.source?.id,
       author = this.author,
       title = this.title ?: "",
       description =this.description,
        url = this.url,
        urlToImage= this.urlToImage,
        publishedAt = this.publishedAt,
       content = this.content,
    )
}

fun List<ArticleDto>.toDomainArticleList(): List<Article>{
    return this.map{it.toDomainArticle()}
}