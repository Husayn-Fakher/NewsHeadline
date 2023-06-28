package com.example.newsapi.feature_topHeadlines.domain.model

// The model for our article object containing only the necessary fields needed do build the UI.
data class Article(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?
)


