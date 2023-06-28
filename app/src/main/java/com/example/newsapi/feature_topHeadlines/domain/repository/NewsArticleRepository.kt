package com.example.newsapi.feature_topHeadlines.domain.repository

import com.example.newsapi.core.Resource
import com.example.newsapi.feature_topHeadlines.domain.model.Article
import kotlinx.coroutines.flow.Flow
// Repository interface to be implemented in the data layer
interface NewsArticleRepository {

    fun getArticles(locale: String): Flow<Resource<List<Article>>>

}