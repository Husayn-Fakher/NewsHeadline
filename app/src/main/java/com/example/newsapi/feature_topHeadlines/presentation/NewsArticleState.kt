package com.example.newsapi.feature_topHeadlines.presentation

import com.example.newsapi.feature_topHeadlines.domain.model.Article

// A wrapper around the articles list letting know the loading status
data class NewsArticleState(
    val newsArticleList: List<Article> = emptyList(),
    val isLoading: Boolean = false
)