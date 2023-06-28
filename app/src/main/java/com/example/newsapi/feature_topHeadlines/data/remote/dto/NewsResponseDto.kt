package com.example.newsapi.feature_topHeadlines.data.remote.dto


data class NewsResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)