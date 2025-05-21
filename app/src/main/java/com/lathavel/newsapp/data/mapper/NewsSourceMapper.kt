package com.lathavel.newsapp.data.mapper

import com.lathavel.newsapp.data.remote.dto.SourceDetailsDto
import com.lathavel.newsapp.domain.model.NewsSource

fun SourceDetailsDto.toDomainNewsSource(): NewsSource{
    return NewsSource(
        sourceId = this.id,
        sourceName = this.name,
        description = this.description,
        url = this.url,
        category = this.category,
        language = this.language,
        country = this.country
    )
}


fun List<SourceDetailsDto>.toDomainNewsSourceList(): List<NewsSource>{
    return this.map {
        it.toDomainNewsSource()
    }
}