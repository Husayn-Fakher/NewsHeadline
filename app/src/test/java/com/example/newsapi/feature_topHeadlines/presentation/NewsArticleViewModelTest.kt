package com.example.newsapi.feature_topHeadlines.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.State
import com.example.newsapi.MainCoroutineRule
import com.example.newsapi.feature_topHeadlines.data.repository.FakeNewsArticleRepositoryImplementation
import com.example.newsapi.feature_topHeadlines.domain.model.Article
import com.example.newsapi.feature_topHeadlines.domain.use_case.GetNewsArticles
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsArticleViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: NewsArticleViewModel
    private lateinit var state: State<NewsArticleState>
    private lateinit var articles: List<Article>

    @Before
    fun setUp() {

        viewModel =
            NewsArticleViewModel(GetNewsArticles(FakeNewsArticleRepositoryImplementation(), "en"))
        state = viewModel.state
        articles = state.value.newsArticleList
    }

    @Test
    fun `viewModel is NotNull`() {

        assertNotNull(viewModel)

    }

    @Test
    fun `state is NotNull`() {

        assertNotNull(state)

    }

    @Test
    fun `articles is NotNull`() {

        assertNotNull(articles)

    }

    @Test
    fun `articles size is 3`() {

        assert(articles.size == 3) { "Expected list size: 3, Actual list size: ${articles.size}" }

    }

    @Test
    fun `article title is correct`() {

        val article= articles.get(0)
        assert(article.title.equals("Article 1")) { "Expected title is: Article 1, Actual title is: ${article.title}" }

    }

    @Test
    fun `article description is correct`() {

        val article= articles.get(0)
        assert(article.description.equals("Article 1 description")) { "Expected description is: Article 1 description, Actual title is: ${article.description}" }

    }

    @Test
    fun `url is correct`() {

        val article= articles.get(0)
        assert(article.url.equals("url")) { "Expected url is: url, Actual url is: ${article.url}" }

    }

    @Test
    fun `image url is correct`() {

        val article= articles.get(0)
        assert(article.urlToImage.equals("image url")) { "Expected image url is: image url, Actual image url is: ${article.urlToImage}" }

    }
}