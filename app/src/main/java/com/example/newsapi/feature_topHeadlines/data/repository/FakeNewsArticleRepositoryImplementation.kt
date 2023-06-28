package com.example.newsapi.feature_topHeadlines.data.repository

import com.example.newsapi.core.Resource
import com.example.newsapi.feature_topHeadlines.domain.model.Article
import com.example.newsapi.feature_topHeadlines.domain.repository.NewsArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// this repo implementation is a fake one for testing purposes
class FakeNewsArticleRepositoryImplementation : NewsArticleRepository {


    val articles = listOf<Article>(
        Article("Article 1", "Article 1 description", "url", "image url"),
        Article("Article 2", "Article 2 description", "url", "image url"),
        Article("Article 3", "Article 3 description", "url", "image url")
    )

    override fun getArticles(locale: String): Flow<Resource<List<Article>>> = flow {

        emit(Resource.Success(articles))

    }
}