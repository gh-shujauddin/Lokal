package com.shqadri.lokal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shqadri.lokal.SharedViewModel
import com.shqadri.lokal.ui.screens.bookmark.BookmarksScreen
import com.shqadri.lokal.ui.screens.jobdetails.JobDetailsScreen
import com.shqadri.lokal.ui.screens.jobs.JobsScreen
import com.shqadri.lokal.ui.screens.jobs.JobsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = hiltViewModel()
    val selectedJob by sharedViewModel.selectedJob.collectAsState()
    val jobsViewModel: JobsViewModel = hiltViewModel()
    val jobsUiState = jobsViewModel.jobsUiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Jobs.route
    ) {
        composable(Screen.Jobs.route) {

            JobsScreen(
                jobsUiState = jobsUiState.value,
                navigateToJobDetail = { job ->
                    sharedViewModel.selectJob(job)
                    navController.navigate(Screen.JobDetailScreen.route)
                },
                onBookmark = {

                },
                onNavItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
        composable(Screen.JobDetailScreen.route) {
            selectedJob?.let {
                JobDetailsScreen(
                    job = it,
                    navigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(Screen.Bookmarks.route) {
            BookmarksScreen(
                onNavItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}