package com.lathavel.newsapp.data.remote.dto

import com.squareup.moshi.Json

data class SourceResponseDto(
    @Json(name = "status") val status: String,
    @Json(name = "sources") val sources: List<SourceDetailsDto>
    )

data class SourceDetailsDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "url") val url: String,
    @Json(name = "category") val category: String,
    @Json(name = "language") val language: String,
    @Json(name = "country") val country: String
)