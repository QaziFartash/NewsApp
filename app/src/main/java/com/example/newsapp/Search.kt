package com.example.newsapp

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.NewsAppTheme
import com.kwabenaberko.newsapilib.models.Article

@Composable
fun Search(newsViewModel: NewsViewModel,
           onHomeClick: () -> Unit,
           onNotificationClick: () -> Unit,
           onSettingsClick: () -> Unit,
           onSearchClick: () -> Unit,
           onBookmarkClick: () -> Unit
//           onBackClick: () -> Unit
)
{
    val newsList by newsViewModel.article.observeAsState(emptyList()) // Observe news data from ViewModel

    Scaffold(
        bottomBar = { // Place the bottom navigation bar at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                SearchBottomNavBar(
                    onNotificationClick = onNotificationClick,
                    onSettingsClick = onSettingsClick,
                    onSearchClick = onSearchClick,
                    onHomeClick = onHomeClick,
                    onBookmarkClick = onBookmarkClick
                )
            }
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Avoid overlapping with bottom navigation
        ){
            TopSearchBar(newsViewModel = newsViewModel)

            Spacer(modifier = Modifier.height(26.dp)) // Add space between the search bar and the news list
            // Display the total number of articles

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp), // Space between title and news list
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = " ${newsList.size}  News",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontSize = 26.sp),
                    modifier = Modifier.padding(horizontal = 24.dp).weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow_forward), // Replace with your forward icon
                    contentDescription = "Forward",
                    modifier = Modifier
                        .size(44.dp) // Icon size
                        .padding(end = 22.dp), // Adjust padding for spacing
                    tint = Color(0xFF141E28).copy(alpha = 1f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(newsList) { article ->
                    NewsItem(article)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(newsViewModel: NewsViewModel) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .clip(RoundedCornerShape(percent = 50)),
            placeholder = { Text("Search here...", color = Color.Gray) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (searchQuery.isNotEmpty()) {
                            newsViewModel.fetchEverythingWithQuery(searchQuery)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.searchtopbar),
                        contentDescription = "Search",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 6.dp),
                        tint = Color.Unspecified
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFF141E28),
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )
    }
}

@Composable
fun NewsItem(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
    {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Composable
fun SearchBottomNavBar(
    onHomeClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit,
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
            BottomNavBarItem2(
                icon = R.drawable.home_icon,
                contentDescription = "Home",
                isSelected = selectedIndex == 0,
                onClick = { selectedIndex = 0
                onHomeClick()
                }
            )
            BottomNavBarItem2(
                icon = R.drawable.path,
                contentDescription = "Bookmark",
                isSelected = selectedIndex == 1,
                onClick = { selectedIndex = 1
                onBookmarkClick()}
            )
            BottomNavBarItem2(
                icon = R.drawable.combined_shape,
                contentDescription = "Search",
                isSelected = selectedIndex == 2,
                onClick = { selectedIndex = 2
                onSearchClick()
                }
            )
            BottomNavBarItem2(
                icon = R.drawable.notifications_icon,
                contentDescription = "Notifications",
                isSelected = selectedIndex == 3,
                onClick = {
                    selectedIndex = 3
                    onNotificationClick()
                }
            )
            BottomNavBarItem2(
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
fun BottomNavBarItem2(
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







@Preview(showBackground = true)
@Composable
fun PreviewSearch() {
    NewsAppTheme {
        SearchPreview()
    }
}