package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        setContent {
//            Home(newsViewModel)
            NewsApp(newsViewModel = newsViewModel)
//            Bookmark(
//                newsViewModel = NewsViewModel(),
//                onArticleClick = {}
//            )
//            Search(
//                newsViewModel,
//                onNotificationClick = {},
//                onSettingsClick = {},
//                onSearchClick = {}
//            )

        }
    }
}

//@Composable
//fun HomePreview(modifier: Modifier = Modifier) {
//    Home(
//        newsViewModel = NewsViewModel(),
//        onArticleClick = {}
//    )
//}

//@Composable
//fun NewsPreview(modifier: Modifier = Modifier) {
//    NewsScreen(
//        article = Article(),
//        onBackClick = {}
//    )
//}

//@Composable
//fun NewsSettingPreview(modifier: Modifier = Modifier) {
//    NewsSetting()
//}

//@Composable
//fun NotificationScreenPreview() {
//    NotificationScreen()
//}

//@Composable
//fun BookmarkPreview() {
//    Bookmark(
//        newsViewModel = NewsViewModel() ,
//        onArticleClick = {}
//    )
//}

@Composable
fun SearchPreview() {
    Search(newsViewModel = NewsViewModel(),
        onHomeClick = {},
        onNotificationClick = {},
        onSettingsClick = {},
        onSearchClick = {},
        onBookmarkClick = {},
        )
}
