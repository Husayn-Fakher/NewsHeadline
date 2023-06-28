package com.example.newsapi.feature_topHeadlines.data.remote.dto

import com.example.newsapi.feature_topHeadlines.domain.model.Article

data class ArticleDto(
    val source: SourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) {
    fun toArticle(): Article {
        return Article(
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage
        )
    }
}