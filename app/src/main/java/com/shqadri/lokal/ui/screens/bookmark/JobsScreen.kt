package com.shqadri.lokal.ui.screens.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shqadri.lokal.ui.navigation.BottomNavBar
import com.shqadri.lokal.ui.navigation.Screen

@Composable
fun BookmarksScreen(onNavItemClick: (String) -> Unit) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = Screen.Bookmarks.route,
                onNavItemClick = onNavItemClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = "Bookmark Screen")
            }
        }
    }
}