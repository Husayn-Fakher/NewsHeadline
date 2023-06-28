package com.example.newsapi.feature_topHeadlines.presentation.screens

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapi.MainActivity
import com.example.newsapi.core.Constants
import com.example.newsapi.feature_topHeadlines.di.AppModule
import com.example.newsapi.feature_topHeadlines.presentation.navigation.Navigation
import com.example.newsapi.ui.theme.NewsAPITheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// Instrumentation and end to end tests
@HiltAndroidTest
@UninstallModules(AppModule::class)
class MainScreenKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
    lateinit var navController: NavController

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {
            navController = rememberNavController()

            NewsAPITheme {
                NavHost(
                    navController = navController as NavHostController,
                    startDestination = Screen.MainScreen.route
                ) {
                    composable(route = Screen.MainScreen.route) {

                        MainScreen(navController as NavHostController)
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
                                defaultValue = Constants.DEFAULT_IMAGE_URL
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
        }
    }


    @Test
    fun newsList_isDisplayed() {

        // Assert the presence of the LazyColumn
        composeRule.onNode(hasTestTag("LazyColumn")).assertIsDisplayed()

    }

    @Test
    fun title_isDisplayed() {

        // Assert the presence of the artilce title
        composeRule.onNodeWithText("Article 1").assertIsDisplayed()

    }


    @Test
    fun navigation_Works() {
        // testing that the navigation works , that we get to the detail screen when an element
        // on the list is clicked
        composeRule.onNodeWithText("Article 1").performClick()
        val route = navController.currentBackStackEntry?.destination?.route
        val routePath = Screen.DetailScreen.route + "/{title}/{description}/{urlToImage}/{url}"
        Assert.assertEquals(route, routePath)

    }
}