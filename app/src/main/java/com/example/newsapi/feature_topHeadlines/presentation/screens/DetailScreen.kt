package com.example.newsapi.feature_topHeadlines.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(title: String?, description: String?, urlToImage: String?, url: String?) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(" ${title}")
        Spacer(modifier = Modifier.size(50.dp))
        Text(" ${description}")

        ShowImage(url = urlToImage, contentDescription = "News Article Image")

        Spacer(modifier = Modifier.size(50.dp))

        Text(
            text = "${url}",
            modifier = Modifier.clickable {
                // Handling the click event
                // Launching the browser to navigate to the news article URL

                coroutineScope.launch {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            }
        )
    }
}


