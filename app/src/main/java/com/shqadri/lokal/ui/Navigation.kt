package com.shqadri.lokal.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shqadri.lokal.model.Screen
import com.shqadri.lokal.model.screens
import com.shqadri.lokal.ui.screens.bookmark.BookmarksScreen
import com.shqadri.lokal.ui.screens.jobs.JobsScreen
import com.shqadri.lokal.ui.screens.jobs.JobsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute by remember { derivedStateOf { navBackStackEntry?.destination?.route } }

    Scaffold(bottomBar = {
        NavigationBar {
            screens.forEach { currentScreen ->
                val isSelected = currentRoute == currentScreen.route
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = if (isSelected) currentScreen.selectedIcon else currentScreen.unselectedIcon,
                            contentDescription = "Jobs"
                        )
                    },
                    label = { Text(currentScreen.titleText) },
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(currentScreen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Jobs.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Jobs.route) {
                val jobsViewModel: JobsViewModel = hiltViewModel()
                val jobsUiState = jobsViewModel.jobsUiState.collectAsState()
                JobsScreen(
                    jobsUiState = jobsUiState.value
                )
            }
            composable(Screen.Bookmarks.route) {
                BookmarksScreen()
            }
        }
    }
}