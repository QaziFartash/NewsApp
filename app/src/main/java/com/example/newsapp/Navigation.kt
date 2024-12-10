package com.example.newsapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NewsApp(newsViewModel: NewsViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            Home(
                newsViewModel = newsViewModel,
                onArticleClick = { article ->
                    navController.navigate("newsDetail/${article.title}")
                },
                onHomeClick = {
                    navController.navigate("home")
                },
                onNotificationClick = {
                    navController.navigate("notifications")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                },
                onSearchClick = {
                    navController.navigate("search")
                },
                onBookmarkClick = {
                    navController.navigate("bookmark")
                }
            )
        }

        composable(
            route = "newsDetail/{articleTitle}",
            arguments = listOf(navArgument("articleTitle") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleTitle = backStackEntry.arguments?.getString("articleTitle")
            val article = newsViewModel.findArticleByTitle(articleTitle)
            if (article != null) {
                NewsScreen(
                    article = article,
                    onBackClick = { navController.popBackStack() }
                )
            } else {
                // Handle the case where the article is not found
                Text("Article not found!")
            }
        }

        // Notifications Screen
        composable("notifications") {
            NotificationScreen(
                onBackClick = { navController.popBackStack() },
                newsViewModel = newsViewModel,
                onHomeClick = {
                    navController.navigate("home")
                },
                onNotificationClick = {
                    navController.navigate("notifications")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                },
                onSearchClick = {
                    navController.navigate("search")
                },
                onBookmarkClick = {
                    navController.navigate("bookmark")
                }
            )
        }

        // Settings Screen
        composable("settings") {
            NewsSetting(
//                onBackClick = { navController.popBackStack() },
                newsViewModel = newsViewModel,
                onHomeClick = {
                    navController.navigate("home")
                },
                onNotificationClick = {
                    navController.navigate("notifications")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                },
                onSearchClick = {
                    navController.navigate("search")
                },
                onBookmarkClick = {
                    navController.navigate("bookmark")
                }
            )
        }
        //Search Screen
        composable("search") {
            Search(
//                onBackClick = { navController.popBackStack() },
                newsViewModel = newsViewModel,
                onHomeClick = {
                    navController.navigate("home")
                },
                onNotificationClick = {
                    navController.navigate("notifications")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                },
                onSearchClick = {
                    navController.navigate("search")
                },
                onBookmarkClick = {
                    navController.navigate("bookmark")
                }
            )
        }
        composable("bookmark") {
            Bookmark(
//                onBackClick = { navController.popBackStack() },
                onArticleClick = { article ->
                    navController.navigate("newsDetail/${article.title}")
                },
                newsViewModel = newsViewModel,
                onHomeClick = {
                    navController.navigate("home")
                },
                onNotificationClick = {
                    navController.navigate("notifications")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                },
                onSearchClick = {
                    navController.navigate("search")
                },
                onBookmarkClick = {
                    navController.navigate("bookmark")
                }
            )
        }
    }
}


