package com.example.newsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.ui.theme.NewsAppTheme
import com.kwabenaberko.newsapilib.models.Article


@Composable
fun Bookmark(newsViewModel: NewsViewModel,
             onArticleClick: (Article) -> Unit,
             onNotificationClick: () -> Unit,
             onSettingsClick: () -> Unit,
             onSearchClick: () -> Unit,
             onHomeClick: () -> Unit,
             onBookmarkClick: () -> Unit) {
    var selectedCategory by remember { mutableStateOf("General") } // Default category
    val articles by newsViewModel.article.observeAsState(emptyList()) // Observing articles

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                BtmNavBar1(onNotificationClick = onNotificationClick,
                    onSettingsClick = onSettingsClick,
                    onSearchClick = onSearchClick,
                    onHomeClick = onHomeClick,
                    onBookmarkClick = onBookmarkClick)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Top bar with icons
            TopBookmarkBar()

            // Scrollable row of news categories
            Spacer(modifier = Modifier.height(16.dp))
            CollectionBlock()
            Spacer(modifier = Modifier.height(16.dp))
            Newscategories(
                newsViewModel = newsViewModel,
                articles = articles,
                onArticleClick = onArticleClick,
                onClick = { category ->
                    selectedCategory = category // Update the selected category
                    newsViewModel.fetchNewsTopHeadline(category) // Fetch news
                }
            )

            // Display articles for the selected category
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Latest bookmarks",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(start = 36.dp, top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$selectedCategory",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 36.dp, top = 16.dp)
            )

            if (articles.isEmpty()) {
                // Show a placeholder if no articles are available
                Text(
                    text = "No articles available.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(articles) { article ->
                        ArticleItems(article = article, onClick = { onArticleClick(article) })
                    }
                }
            }
        }
    }
}
@Composable
fun ArticleItems(article: Article, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(article.urlToImage), // Load image
                contentDescription = article.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = article.description ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }
        }
    }
}



@Composable
fun TopBookmarkBar()
{
    Row (
        modifier = Modifier
            .padding(top = 50.dp, start = 50.dp, end = 50.dp)
            .fillMaxWidth(), // Make the Row take full width
        horizontalArrangement = Arrangement.SpaceBetween, // Distribute elements
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.menu), // Image resource
            contentDescription = "Menu_image"                     // How the image should scale
        )
        Image(
            painter = painterResource(id = R.drawable.podcast_icon), // Image resource
            contentDescription = "podcast_icon",
            alignment = Alignment.TopEnd                    // How the image should scale
        )
    }
}

@Composable
fun CollectionBlock() {
    Column{
        // Title
        Text(
            text = "Collections",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 36.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 36.dp, top = 20.dp)
        )
    }
}

@Composable
fun Newscategories(
    newsViewModel: NewsViewModel,
    articles: List<Article>,
    onArticleClick: (Article) -> Unit,
    onClick: (String) -> Unit // Pass the category name
) {
    val categoriesList = listOf(
        NewsCategory("General", R.drawable.general_news),
        NewsCategory("Sports", R.drawable.sport_news),
        NewsCategory("Entertainment", R.drawable.entertainment_news),
        NewsCategory("Science", R.drawable.science_news),
        NewsCategory("Health", R.drawable.health_news),
        NewsCategory("Technology", R.drawable.tech_news)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 8.dp, end = 8.dp), // Add padding to the row
        verticalAlignment = Alignment.CenterVertically
    ) {
        categoriesList.forEach { category ->
            Card(
                modifier = Modifier
                    .padding(start = 36.dp, top = 8.dp)
                    .size(140.dp) // Uniform card size
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onClick(category.name) },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray) // Set card color
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = category.imageUrl),
                        contentDescription = "${category.name} image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.4f)) // 40% transparency
                    )
                    Text(
                        text = category.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.Center) // Center the text over the image
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}



@Composable
fun BtmNavBar1(
    onNotificationClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit,
    onHomeClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    // Track the currently selected item
    var selectedIndex by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier
            .padding(bottom = 50.dp, start = 30.dp, end = 30.dp)
            .width(287.dp)
            .height(56.dp)
            .shadow(
                elevation = 12.dp, // Adjust elevation for stronger shadow
                shape = RoundedCornerShape(40.dp),
                clip = false // Ensures the shadow doesn't get clipped
            )
            .clip(RoundedCornerShape(40.dp)),
        color = Color.White, // Background color
        shadowElevation = 70.dp // Elevation
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Add navigation items with selection logic
            BottomNavItem4(
                icon = R.drawable.home_icon,
                contentDescription = "Home",
                isSelected = selectedIndex == 0,
                onClick = { selectedIndex = 0
                onHomeClick()
                }
            )
            BottomNavItem4(
                icon = R.drawable.path,
                contentDescription = "Bookmark",
                isSelected = selectedIndex == 1,
                onClick = { selectedIndex = 1
                    onBookmarkClick()
                }
            )
            BottomNavItem4(
                icon = R.drawable.combined_shape,
                contentDescription = "Search",
                isSelected = selectedIndex == 2,
                onClick = { selectedIndex = 2
                onSearchClick()}
            )
            BottomNavItem4(
                icon = R.drawable.notifications_icon,
                contentDescription = "Notifications",
                isSelected = selectedIndex == 3,
                onClick = { selectedIndex = 3
                onNotificationClick()}
            )
            BottomNavItem4(
                icon = R.drawable.search_grey,
                contentDescription = "Settings",
                isSelected = selectedIndex == 4,
                onClick = { selectedIndex = 4
                onSettingsClick()}
            )
        }
    }
}

@Composable
fun BottomNavItem4(
    icon: Int,
    contentDescription: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            tint = if (isSelected) Color.Black else Color.Gray, // Change color based on selection
            modifier = Modifier.size(24.dp) // Adjust icon size
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewBookmark() {
//    NewsAppTheme {
//        BookmarkPreview()
//    }
//}