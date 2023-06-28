package com.example.newsapi.feature_topHeadlines.data.remote

import com.example.newsapi.BuildConfig
import com.example.newsapi.feature_topHeadlines.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    // Query to get the news list
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("language") countryCode: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponseDto


}