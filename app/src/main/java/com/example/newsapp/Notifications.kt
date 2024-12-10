package com.example.newsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun NotificationScreen(
    newsViewModel: NewsViewModel,
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onHomeClick: () -> Unit)
{
    Scaffold(
        bottomBar = { BottomNavigationBar1(
            onNotificationClick = onNotificationClick,
            onSettingsClick = onSettingsClick,
            onSearchClick = onSearchClick,
            onBookmarkClick = onBookmarkClick,
            onHomeClick = onHomeClick) } // Add the bottom navigation bar
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Avoid overlapping with the BottomBar
        ) {
            TopAppBar()
            Spacer(modifier = Modifier.height(20.dp))
            NotificationBlock1()
            Spacer(modifier = Modifier.height(20.dp))
            Textsurface()
            Textsurface2()
        }
    }
}




@Composable
fun TopAppBar()
{
    Row (
        modifier = Modifier
            .padding(top = 50.dp, start = 50.dp, end = 40.dp)
            .fillMaxWidth(), // Make the Row take full width
        horizontalArrangement = Arrangement.SpaceBetween, // Distribute elements
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.menu), // Image resource
            contentDescription = "Menu_image"                     // How the image should scale
        )
        Image(
            painter = painterResource(id = R.drawable.search_icon), // Image resource
            contentDescription = "podcast_icon",
            alignment = Alignment.TopEnd                    // How the image should scale
        )
    }
}

@Composable
fun NotificationBlock1() {
    Column{
        // Title
        Text(
            text = "Unread",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 36.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 36.dp, top = 20.dp)
        )
    }
}

@Composable
fun Textsurface() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp)
            .height(234.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth() // Column matches the full width of the parent
        ) {
            Text(
                text = "TECHNOLOGY",
                fontWeight = FontWeight.SemiBold,
                color = Color(0X7A141E28),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 36.dp, top = 16.dp) // Specific padding for the text
                    .fillMaxWidth()
            )
            Text(
                text = "Insurtech startup PaserPolis gets $54 million_____Series B",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 36.dp)
            )
            Spacer(modifier = Modifier.height(22.dp))
            HorizontalDivider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth() // Ensure full width here
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "BUSINESS",
                fontWeight = FontWeight.SemiBold,
                color = Color(0X7A141E28),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 36.dp) // Padding specific to this text
                    .fillMaxWidth()
            )
            Text(
                text = "Hypatos gets $11.8M for a deep learning approach",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 36.dp)
            )
        }
    }
}

@Composable
fun Textsurface2() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth() // Optional spacing around content
        ) {
            item {
                Text(
                    text = "3 December,2024",
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 36.dp, top = 16.dp)
                        .fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "BUSINESS",
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0X7A141E28),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 36.dp, top = 16.dp)
                        .fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "The IPO parade continues as Wish files, Bumble targets continue as...",
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 36.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            repeat(10) { // Example: Repeat items for scrolling demonstration
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "BUSINESS",
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0X7A141E28),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 36.dp)
                            .fillMaxWidth()
                    )
                }
                item {
                    Text(
                        text = "The IPO parade continues as Wish files, Bumble targets continue as...",
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 16.sp
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 36.dp)
                    )
                }
            }
        }
    }
}





@Composable
fun BottomNavigationBar1( onNotificationClick: () -> Unit,
                          onSettingsClick: () -> Unit,
                          onSearchClick: () -> Unit,
                          onBookmarkClick: () -> Unit,
                          onHomeClick: () -> Unit)
{
    var selectedIndex by remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp, start = 40.dp, end = 40.dp)
            .width(320.dp)
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
//            horizontalArrangement = Arrangement.SpaceAround,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
            // Space between icons
        ) {
            // Add navigation items
            BottomNavItem1(
                icon = R.drawable.home_icon,
                contentDescription = "Home",
                isSelected = selectedIndex == 0,
                onClick = { selectedIndex = 0
                        onHomeClick()
                }
            )
            BottomNavItem1(
                icon = R.drawable.path,
                contentDescription = "Bookmark",
                isSelected = selectedIndex == 1,
                onClick = { selectedIndex = 1
                onBookmarkClick()}
            )
            BottomNavItem1(
                icon = R.drawable.combined_shape,
                contentDescription = "Search",
                isSelected = selectedIndex == 2,
                onClick = { selectedIndex = 2
                    onSearchClick()
                }
            )
            BottomNavItem1(
                icon = R.drawable.notifications_icon,
                contentDescription = "Notifications",
                isSelected = selectedIndex == 3,
                onClick = {
                    selectedIndex = 3
                    onNotificationClick()
                }
            )
            BottomNavItem1(
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
fun BottomNavItem1(icon: Int,
                   contentDescription: String,
                   isSelected: Boolean,
                   onClick: () -> Unit) {
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
//fun PreviewNotificationScreen() {
//    NewsAppTheme {
//        NotificationScreenPreview()
//    }
//}