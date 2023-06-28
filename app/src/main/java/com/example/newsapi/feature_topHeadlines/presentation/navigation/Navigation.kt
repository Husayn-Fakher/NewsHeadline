package com.example.newsapi.feature_topHeadlines.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapi.feature_topHeadlines.presentation.screens.MainScreen
import com.example.newsapi.feature_topHeadlines.presentation.screens.Screen
import com.example.newsapi.core.Constants.DEFAULT_IMAGE_URL
import com.example.newsapi.feature_topHeadlines.presentation.screens.DetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {

            MainScreen(navController)
        }

        composable(route = Screen.DetailScreen.route + "/{title}/{description}/{urlToImage}/{url}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = "No title"
                    nullable = true
                },
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = "No description"
                    nullable = true

                },

                navArgument("urlToImage") {
                    type = NavType.StringType
                    defaultValue = DEFAULT_IMAGE_URL
                    nullable = true

                },

                navArgument("url") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true

                }
            )
        ) { entry ->
            DetailScreen(
                title = entry.arguments?.getString("title"),
                description = entry.arguments?.getString("description"),
                urlToImage = entry.arguments?.getString("urlToImage"),
                url = entry.arguments?.getString("url")
            )
        }
    }
}