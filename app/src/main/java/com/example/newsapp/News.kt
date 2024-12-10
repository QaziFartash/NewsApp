package com.example.newsapp

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article



@Composable
fun NewsScreen(
    article: Article,
    onBackClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp), // Optional padding for elevation or spacing
                contentAlignment = Alignment.Center
            ) {
                BtmNavBar()
            }
        } // Add the bottom navigation bar
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Avoid overlapping with the BottomNavBar
        ) {
            // Box to overlay IconButton on the image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
            ) {
                // Article Image with rounded bottom corners
                AsyncImage(
                    model = article.urlToImage ?: "https://as1.ftcdn.net/v2/jpg/04/62/93/66/1000_F_462936689_BpEEcxfgMuYPfTaIAOC1tCDurmsno7Sp.jpg",
                    contentDescription = "Article Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 26.dp,
                                bottomEnd = 26.dp
                            )
                        ),
                    contentScale = ContentScale.Crop
                )

                // Back button positioned on top of the image
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .padding(top = 42.dp, start = 40.dp)
                        .align(Alignment.TopStart) // Aligns to the top-left of the image
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "Back",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(45.dp))

            // Technology Text with uniform padding
            Text(
                text = "TECHNOLOGY",
                fontWeight = FontWeight.Bold,
                color = Color(0x7A141E28),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 40.dp) // Uniform padding
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Article Title
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 40.dp) // Uniform padding
            )

            Spacer(modifier = Modifier.height(15.dp))
            HorizontalDivider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            // Article Description
            Text(
                text = article.content ?: "No details available",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = 12.dp)
                    .fillMaxWidth(), // Uniform padding
                maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Visible
            )
        }
    }
}

@Composable
fun BtmNavBar() {
    // Track the currently selected item
    var selectedIndex by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier
//            .fillMaxWidth()
            .padding(bottom = 60.dp, start = 30.dp, end = 30.dp)
            .width(193.dp)
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
//                .width(193.dp)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Add navigation items with selection logic
            BottomNavItem3(
                icon = R.drawable.chatbtn,
                contentDescription = "Home",
                isSelected = selectedIndex == 0,
                onClick = { selectedIndex = 0 }
            )
            BottomNavItem3(
                icon = R.drawable.path,
                contentDescription = "Bookmark",
                isSelected = selectedIndex == 1,
                onClick = { selectedIndex = 1 }
            )
            BottomNavItem3(
                icon = R.drawable.forwardarrow,
                contentDescription = "Search",
                isSelected = selectedIndex == 2,
                onClick = { selectedIndex = 2 }
            )
        }
    }
}

@Composable
fun BottomNavItem3(
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


//@Composable
//fun NewsScreen(
//    article: Article,
//    onBackClick: () -> Unit
//) {
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        // Box to overlay IconButton on the image
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(375.dp)
//        ) {
//            // Article Image with rounded bottom corners
//            AsyncImage(
//                model = article.urlToImage ?: "https://as1.ftcdn.net/v2/jpg/04/62/93/66/1000_F_462936689_BpEEcxfgMuYPfTaIAOC1tCDurmsno7Sp.jpg",
//                contentDescription = "Article Image",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(
//                        RoundedCornerShape(
//                            topStart = 0.dp,
//                            topEnd = 0.dp,
//                            bottomStart = 26.dp,
//                            bottomEnd = 26.dp
//                        )
//                    ),
//                contentScale = ContentScale.Crop
//            )
//
//            // Back button positioned on top of the image
//            IconButton(
//                onClick = onBackClick,
//                modifier = Modifier
//                    .padding(top = 42.dp, start = 40.dp)
//                    .align(Alignment.TopStart) // Aligns to the top-left of the image
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.back_icon),
//                    contentDescription = "Back",
//                    modifier = Modifier.size(50.dp)
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(45.dp))
//
//        // Technology Text with uniform padding
//        Text(
//            text = "TECHNOLOGY",
//            fontWeight = FontWeight.Bold,
//            color = Color(0x7A141E28),
//            style = MaterialTheme.typography.titleMedium.copy(
//                fontSize = 12.sp,
//                fontWeight = FontWeight.Bold
//            ),
//            textAlign = TextAlign.Start,
//            modifier = Modifier.padding(horizontal = 40.dp) // Uniform padding
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        // Article Title
//        Text(
//            text = article.title,
//            style = MaterialTheme.typography.titleMedium.copy(
//                fontSize = 22.sp,
//                fontWeight = FontWeight.Bold
//            ),
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(horizontal = 40.dp) // Uniform padding
//        )
//
//        Spacer(modifier = Modifier.height(15.dp))
//        Spacer(modifier = Modifier.height(10.dp))
//        HorizontalDivider(
//                color = Color.Gray,
//                thickness = 1.dp,
//                modifier = Modifier.padding(horizontal = 40.dp)
//            )
//        Spacer(modifier = Modifier.height(30.dp))
//        // Article Description
//        Text(
//            text = article.content ?: "No details available",
//            style = MaterialTheme.typography.bodyMedium.copy(
//                fontSize = 16.sp
//            ),
////            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//            modifier = Modifier.padding(horizontal = 40.dp, vertical = 12.dp).fillMaxWidth(),// Uniform padding
//            maxLines = Int.MAX_VALUE,
//            overflow = TextOverflow.Visible
//        )
//    }
//}


//@Preview(showBackground = true)
//@Composable
//fun PreviewNews() {
//    NewsAppTheme {
//        NewsPreview()
//    }
//}

