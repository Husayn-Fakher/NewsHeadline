package com.example.newsapi.feature_topHeadlines.data.repository

import android.util.Log
import com.example.newsapi.core.Resource
import com.example.newsapi.feature_topHeadlines.data.remote.NewsApiService
import com.example.newsapi.feature_topHeadlines.domain.model.Article
import com.example.newsapi.feature_topHeadlines.domain.repository.NewsArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

//news repository implementation
class NewsArticleRepositoryImplementation(
    private val api: NewsApiService,
) : NewsArticleRepository {
    override fun getArticles(locale: String): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())

// Making the API request
        try {
            val newsResponse = api.getTopHeadlines(locale)
            // Mapping each article response to an Article object to be displayed in the UI
            val articles = newsResponse.articles.map { it.toArticle() }
            // Emitting the success response containing the list of artciles
            emit(Resource.Success(articles))
        } catch (e: HttpException) {

            emit(
                Resource.Error(
                    message = "Something went wrong",
                )
            )
        } catch (e: IOException) {


            emit(
                Resource.Error(
                    message = "Could not reach server, check your internet connection",

                    )
            )
        }
    }
}

