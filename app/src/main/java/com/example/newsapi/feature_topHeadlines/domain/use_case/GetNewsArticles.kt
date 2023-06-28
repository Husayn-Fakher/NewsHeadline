package com.example.newsapi.feature_topHeadlines.domain.use_case

import com.example.newsapi.core.Resource
import com.example.newsapi.feature_topHeadlines.domain.model.Article
import com.example.newsapi.feature_topHeadlines.domain.repository.NewsArticleRepository
import kotlinx.coroutines.flow.Flow
// Use case to get the articles form the API server  .
class GetNewsArticles(private val repository: NewsArticleRepository, val locale: String) {

    operator fun invoke( ): Flow<Resource<List<Article>>> {

        return repository.getArticles(locale)

    }
}