package com.example.newsapi.feature_topHeadlines.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapi.core.Utils
import com.example.newsapi.feature_topHeadlines.presentation.NewsArticleState
import com.example.newsapi.feature_topHeadlines.presentation.NewsArticleViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    val viewModel: NewsArticleViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NewsArticleViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldHostState.showSnackbar(
                        message = event.message
                    )
                }

                else -> {}
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(scaffoldHostState) })
    { paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            contentAlignment = Alignment.Center,
        ) {
            if (state.isLoading) {
                ShowCircularProgressIndicator()
            }
            Column(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                DisplayData(state, navController)
            }
        }
    }
}


@Composable
fun ShowCircularProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(250.dp)
            .padding(16.dp).
            testTag("CircularProgressIndicator"),
        strokeWidth = 8.dp
    )
}

@Composable
fun DisplayData(state: NewsArticleState, navController: NavHostController) {
    val lazyListState = rememberLazyListState()

    val articles = state.newsArticleList

    LazyColumn(state = lazyListState, modifier = Modifier.testTag("LazyColumn")) {
        items(articles) { item ->

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        // Navigate to the detail view when item is clicked
                        navController.navigate(
                            Screen.DetailScreen.withArgs(
                                item.title.toString(),
                                item.description.toString(),
                                Utils.encodeImageUrl(item.urlToImage),
                                Utils.encodeNewsUrl(item.url)
                            )
                        )
                    }
            ) {
                ShowImage(url = item.urlToImage, contentDescription = "News Article Image")
                Text(text = item.title.toString(), modifier = Modifier.testTag("Title"))
            }
        }
    }
}

@Composable
fun ShowImage(url: String?, contentDescription: String) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .build(),
        contentDescription = contentDescription
    )
}