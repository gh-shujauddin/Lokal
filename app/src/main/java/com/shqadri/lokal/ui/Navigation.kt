package com.shqadri.lokal.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shqadri.lokal.model.Screen
import com.shqadri.lokal.ui.bookmark.BookmarksScreen
import com.shqadri.lokal.ui.jobs.JobsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(imageVector = Icons.Default.Work, contentDescription = "Jobs") },
                    label = { Text("Jobs") },
                    selected = currentRoute(navController) == Screen.Jobs.route,
                    onClick = { navController.navigate(Screen.Jobs.route) }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = "Bookmarks"
                        )
                    },
                    label = { Text("Bookmarks") },
                    selected = currentRoute(navController) == Screen.Bookmarks.route,
                    onClick = { navController.navigate(Screen.Bookmarks.route) }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Jobs.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Jobs.route) {
                JobsScreen()
            }
            composable(Screen.Bookmarks.route) {
                BookmarksScreen()
            }
        }
    }
}

private fun currentRoute(navController: NavHostController): String? =
    navController.currentBackStackEntry?.destination?.route

