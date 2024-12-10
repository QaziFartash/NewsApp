package com.example.newsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kwabenaberko.newsapilib.models.Article

@Composable
fun NewsSetting(newsViewModel: NewsViewModel,
                onHomeClick: () -> Unit,
                onNotificationClick: () -> Unit,
                onSettingsClick: () -> Unit,
                onBookmarkClick: () -> Unit,
                onSearchClick: () -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar(
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
            TopSetBar()
            Spacer(modifier = Modifier.height(20.dp))
            Block1()
            Spacer(modifier = Modifier.height(20.dp))
            Block2()
        }
    }
}

@Composable
fun TopSetBar()
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
fun Block1() {
    Column(
        modifier = Modifier.padding(start = 25.dp, end = 16.dp) // Uniform padding for the block
    ) {
        // Title
        Text(
            text = "Settings",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 36.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 22.dp, top = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        // List of rows (Profile, News, Notifications, Subscriptions)
        listOf(
            Pair(R.drawable.profilesetting, "Profile settings" to "Settings regarding your profile"),
            Pair(R.drawable.newssetting, "News settings" to "Choose your favourite topics"),
            Pair(R.drawable.notifications, "Notifications" to "When would you like to be notified"),
            Pair(R.drawable.subscripotion, "Subscriptions" to "Currently, you are in Starter Plan")
        ).forEach { (icon, textPair) ->
            SettingsRow(icon, textPair.first, textPair.second)
            Spacer(modifier = Modifier.height(16.dp)) // Space between rows
        }
    }
}

@Composable
fun SettingsRow(
    iconId: Int,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Ensure the row adjusts to the content's height
            .padding(horizontal = 16.dp), // Uniform horizontal padding
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.width(16.dp)) // Space between icon and text

        // Title and subtitle
        Column(
            modifier = Modifier
                .weight(1f) // Take remaining space
                .padding(vertical = 8.dp) // Align vertically within the row
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(8.dp)) // Space between title and subtitle
            Text(
                text = subtitle,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
            )
        }

        // Arrow icon
        Icon(
            painter = painterResource(id = R.drawable.arrow_forward),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 6.dp), // Padding from the end of the row
            tint = Color(0xFF141E28)
        )
    }
}

@Composable
fun Block2() {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 16.dp) // Uniform padding
    ) {
        Text(
            text = "Other",
            fontWeight = FontWeight.Light,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 26.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 22.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        listOf(
            Pair(R.drawable.bug, "Bug report" to "Report Bugs very easy"),
            Pair(R.drawable.share, "Share the app" to "Share on social media networks")
        ).forEach { (icon, textPair) ->
            SettingsRow2(icon, textPair.first, textPair.second)
            Spacer(modifier = Modifier.height(16.dp)) // Space between rows
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SettingsRow2(
    iconId: Int,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Ensure the row adjusts to the content's height
            .padding(horizontal = 16.dp), // Uniform horizontal padding
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.width(16.dp)) // Space between icon and text

        // Title and subtitle
        Column(
            modifier = Modifier
                .weight(1f) // Take remaining space
                .padding(vertical = 8.dp) // Align vertically within the row
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(8.dp)) // Space between title and subtitle
            Text(
                text = subtitle,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
            )
        }

        // Arrow icon
        Icon(
            painter = painterResource(id = R.drawable.arrow_forward),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 6.dp), // Padding from the end of the row
            tint = Color(0xFF141E28)
        )
    }
}

@Composable
fun BottomNavigationBar(
    onHomeClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 36.dp,start = 40.dp , end=40.dp)
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
                BottomNavItem(
                    icon = R.drawable.home_icon,
                    contentDescription = "Home",
                    isSelected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                            onHomeClick()
                    }
                )
                BottomNavItem(
                    icon = R.drawable.path,
                    contentDescription = "Bookmark",
                    isSelected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                    onBookmarkClick()
                    }
                )
                BottomNavItem(
                    icon = R.drawable.combined_shape,
                    contentDescription = "Search",
                    isSelected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        onSearchClick()

                    }
                )
                BottomNavItem(
                    icon = R.drawable.notifications_icon,
                    contentDescription = "Notifications",
                    isSelected = selectedIndex == 3,
                    onClick = {
                        selectedIndex = 3
                        onNotificationClick()
                    }
                )
                BottomNavItem(
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
fun BottomNavItem( icon: Int,
                   contentDescription: String,
                   isSelected: Boolean,
                   onClick: () -> Unit) {
    IconButton(onClick =  onClick ) {
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
//fun PreviewNewsSetting() {
//    NewsAppTheme {
//        NewsSettingPreview()
//    }
//}