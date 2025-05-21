package com.lathavel.newsapp.data.remote.dto

import com.squareup.moshi.Json

data class TopHeadLinesResponseDto (
    @Json(name = "status") val status: String,
    @Json(name = "totalResults") val totalResults: Int,
    @Json(name = "articles") val articles: List<ArticleDto>?,

)

data class ArticleDto(
    @Json(name = "source") val source: SourceDto?,
    @Json(name = "author") val author: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "urlToImage") val urlToImage: String?,
    @Json(name = "publishedAt") val publishedAt: String?,
    @Json(name = "content") val content: String?,

)

data class SourceDto (
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?
)

//*
// {"status":"ok","totalResults":32,
// "articles":[
// {"source":{"id":"associated-press","name":"Associated Press"},
// "author":"Tia Goldenberg, Samy Magdy, Wafaa Shurafa",
// "title":"Israel's Netanyahu says allies pressed him to resume some aid to Gaza. So far, nothing has gone in - AP News",
// "description":"Israeli Prime Minister Benjamin Netanyahu has acknowledged that his decision to resume limited aid to Gaza came from pressure from allies. In a video statement posted to social media, Netanyahu said on Monday that Israel’s allies had voiced concern about “ima…",
// "url":"https://apnews.com/article/mideast-wars-israel-gaza-hamas-05-19-2025-da619d393510e0dde9650adbd39cbf0b",
// "urlToImage":"https://dims.apnews.com/dims4/default/e14c831/2147483647/strip/true/crop/5616x3159+0+293/resize/1440x810!/quality/90/?url=https%3A%2F%2Fassets.apnews.com%2F18%2Fc4%2Fd4e40bf6e70610e4055ca4ccacb4%2F6915a89551fa46caa34c99883d439425",
// "publishedAt":"2025-05-19T11:38:00Z",
// "content":"TEL AVIV, Israel (AP) Israeli Prime Minister Benjamin Netanyahu said Monday that his decision to resume limited aid to Gaza after a weekslong blockade came after pressure from allies who said they wo… [+5475 chars]"},
// */