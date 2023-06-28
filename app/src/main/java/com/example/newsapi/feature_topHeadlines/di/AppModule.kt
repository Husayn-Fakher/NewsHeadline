package com.example.newsapi.feature_topHeadlines.di

import android.app.Application
import android.content.Context
import android.os.Build
import com.example.newsapi.core.Constants.BASE_URL
import com.example.newsapi.feature_topHeadlines.data.remote.NewsApiService
import com.example.newsapi.feature_topHeadlines.data.repository.NewsArticleRepositoryImplementation
import com.example.newsapi.feature_topHeadlines.domain.repository.NewsArticleRepository
import com.example.newsapi.feature_topHeadlines.domain.use_case.GetNewsArticles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import javax.inject.Singleton

// The dependency injection module providing the project's dependencies

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }


    @Provides
    @Singleton
    fun provideLanguageCode(context: Context): String {
        val locale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales.get(0)
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale
        }
        return locale.language
    }

    @Provides
    @Singleton
    fun provideGetNewsArticlesUseCase(
        repository: NewsArticleRepository, languageCode: String
    ): GetNewsArticles {
        return GetNewsArticles(repository, languageCode)
    }

    @Provides
    @Singleton
    fun provideNewsArticleRepository(
        api: NewsApiService,
    ): NewsArticleRepository {
        return NewsArticleRepositoryImplementation(api)
    }

    @Provides
    fun provideNewsApiService(): NewsApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NewsApiService::class.java)
    }
}