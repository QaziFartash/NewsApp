package com.example.newsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//@Composable
//fun Home(newsViewModel: NewsViewModel, onArticleClick: (Article) -> Unit) {
//    Scaffold(
//        bottomBar = { // Place the bottom navigation bar at the bottom
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 20.dp), // Optional padding for elevation or spacing
//                contentAlignment = Alignment.Center
//            ) {
//                BottomNavBar()
//            }
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding) // Avoid overlapping with bottom navigation
//        ) {
//            TopBar()
//            Spacer(modifier = Modifier.height(20.dp))
//            NewsImage(newsViewModel = newsViewModel, onArticleClick = onArticleClick)
//            LatestNewsBlock(newsViewModel, onArticleClick)
//        }
//    }
//}

@Composable
fun Home(
    newsViewModel: NewsViewModel,
    onArticleClick: (Article) -> Unit,
    onNotificationClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit,
    onHomeClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    Scaffold(
        bottomBar = { // Place the bottom navigation bar at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                BottomNavBar(
                    onNotificationClick = onNotificationClick,
                    onSettingsClick = onSettingsClick,
                    onSearchClick = onSearchClick,
                    onHomeClick = onHomeClick,
                    onBookmarkClick = onBookmarkClick
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Avoid overlapping with bottom navigation
        ) {
            TopBar()
            Spacer(modifier = Modifier.height(20.dp))
            NewsImage(newsViewModel = newsViewModel, onArticleClick = onArticleClick)
            LatestNewsBlock(newsViewModel, onArticleClick)
        }
    }
}



@Composable
fun TopBar()
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
        Text(
            text =  buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                    append("News")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("App")
                }
            },
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 16.sp)
        )
        Image(
            painter = painterResource(id = R.drawable.podcast_icon), // Image resource
            contentDescription = "podcast_icon",
            alignment = Alignment.TopEnd                    // How the image should scale
        )
    }
}

@Composable
fun NewsImage(newsViewModel: NewsViewModel, onArticleClick: (Article) -> Unit) {
    val articles by newsViewModel.article.observeAsState(emptyList())
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(articles) { article ->
            ArticleItem(article = article, onClick = { onArticleClick(article) })
        }
    }
}


@Composable
fun ArticleItem(article: Article , onClick: () -> Unit) {
    // Format the time (assuming `article.publishedAt` is a String or Date object)
    val publishedTime = article.publishedAt?.let { parseTime(it) } ?: "Unknown time"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(311.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
//            .clickable(onClick = onClick),
//            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Keeps the card aspect ratio consistent
        ) {
            // Background image
            AsyncImage(
                model = if (!article.urlToImage.isNullOrBlank()) article.urlToImage else "https://as1.ftcdn.net/v2/jpg/04/62/93/66/1000_F_462936689_BpEEcxfgMuYPfTaIAOC1tCDurmsno7Sp.jpg",
                contentDescription = "Article image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Semi-transparent overlay to make the image appear transparent but still provide contrast for the text
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)) // Increase alpha for better contrast
            )

            // Row for category and time
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, top = 45.dp)
            ) {
                Text(
                    text = "TECHNOLOGY",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.weight(1f))

                // Time of the article (only time, no date) - Positioned at the top of the card
                Text(
                    text = publishedTime,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(end = 16.dp)
                )
            }

            // Title positioned just above the bottom bar
            Text(
                text = article.title,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Start,
                maxLines = 3, // Limit to 3 lines
                overflow = TextOverflow.Ellipsis, // Add ellipsis if overflow occurs
                modifier = Modifier
                    .align(Alignment.BottomStart) // Align to the bottom-left just above the bottom bar
                    .padding(start = 20.dp, bottom = 70.dp, end = 16.dp) // Add padding to position it above the bottom bar
                    .fillMaxWidth(0.8f) // Optional: Limit width for better layout
            )

            // Bottom bar with icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chatbtn), // Replace with your chat icon
                        contentDescription = "Chat",
                        modifier = Modifier
                            .size(34.dp)
                            .padding(start = 10.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.savebtn), // Replace with your save icon
                        contentDescription = "Save",
                        modifier = Modifier.size(24.dp)
                            .clickable(onClick = onClick),
                        tint = Color.White
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.forwardarrow), // Replace with your forward icon
                    contentDescription = "Forward",
                    modifier = Modifier
                        .size(34.dp)
                        .padding(end = 10.dp)
                        .clickable(onClick = onClick),
                    tint = Color.White
                )
            }
        }
    }
}

// Helper function to format the time (using SimpleDateFormat)
fun parseTime(date: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault()) // 12-hour format with AM/PM
        val parsedDate = inputFormat.parse(date)
        outputFormat.format(parsedDate ?: Date())
    } catch (e: ParseException) {
        "Unknown time"
    }
}

@Composable
fun LatestNewsBlock(newsViewModel: NewsViewModel , onArticleClick: (Article) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 40.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp), // Space between title and news list
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Latest News",
                fontWeight = FontWeight.Bold,
                color = Color(0X7A141E28),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(1f)
            )

            Icon(
                painter = painterResource(id = R.drawable.arrow_forward), // Replace with your forward icon
                contentDescription = "Forward",
                modifier = Modifier
                    .size(34.dp) // Icon size
                    .padding(end = 12.dp), // Adjust padding for spacing
                tint = Color(0xFF141E28).copy(alpha = 1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Technology News Section
        SectionWithTitle(
            articles = newsViewModel.techArticles.observeAsState(emptyList()).value,
            onArticleClick = onArticleClick
        )
    }
}

@Composable
fun SectionWithTitle(
    articles: List<Article>,
    onArticleClick: (Article) -> Unit
//    color: Color = Color.Unspecified
) {
    Column(modifier = Modifier.fillMaxWidth())
    {
        // News articles list
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(articles) { article ->
                ArticleItem2(article = article , onClick = { onArticleClick(article) })
            }
        }
    }
}

@Composable
fun ArticleItem2(article: Article , onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
        .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Article image
            AsyncImage(
                model = article.urlToImage ?: "https://as1.ftcdn.net/v2/jpg/04/62/93/66/1000_F_462936689_BpEEcxfgMuYPfTaIAOC1tCDurmsno7Sp.jpg",
                contentDescription = "Article image",
                modifier = Modifier.size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Article title
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // "Technology" in dark gray
                Text(
                    text = "TECHNOLOGY",
                    fontWeight = FontWeight.Bold,
                    color = Color(0X7A141E28),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
@Composable
fun BottomNavBar(
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
            .padding(bottom = 60.dp, start = 30.dp, end = 30.dp)
            .width(287.dp)
            .height(56.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(40.dp),
                clip = false
            )
            .clip(RoundedCornerShape(40.dp)),
        color = Color.White,
        shadowElevation = 70.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem2(
                icon = R.drawable.home_icon,
                contentDescription = "Home",
                isSelected = selectedIndex == 0,
                onClick = { selectedIndex = 0
                    onHomeClick()
                }
            )
            BottomNavItem2(
                icon = R.drawable.path,
                contentDescription = "Bookmark",
                isSelected = selectedIndex == 1,
                onClick = { selectedIndex = 1
                onBookmarkClick()}
            )
            BottomNavItem2(
                icon = R.drawable.combined_shape,
                contentDescription = "Search",
                isSelected = selectedIndex == 2,
                onClick = { selectedIndex = 2
                    onSearchClick()

                }
            )
            BottomNavItem2(
                icon = R.drawable.notifications_icon,
                contentDescription = "Notifications",
                isSelected = selectedIndex == 3,
                onClick = {
                    selectedIndex = 3
                    onNotificationClick()
                }
            )
            BottomNavItem2(
                icon = R.drawable.search_grey,
                contentDescription = "Settings",
                isSelected = selectedIndex == 4,
                onClick = {
                    selectedIndex = 4
                    onSettingsClick()
                }
            )
        }
    }
}

@Composable
fun BottomNavItem2(
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
//fun PreviewHome() {
//    NewsAppTheme {
//        HomePreview()
//    }
//}

