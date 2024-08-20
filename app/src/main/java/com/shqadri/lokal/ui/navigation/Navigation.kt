package com.shqadri.lokal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shqadri.lokal.SharedViewModel
import com.shqadri.lokal.ui.screens.bookmark.BookmarkViewModel
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

    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()
    val bookmarkedJobs = bookmarkViewModel.bookmarkedJobs.collectAsState()
    val bookmarkedJobsId = bookmarkViewModel.bookmarkedJobIds.collectAsState().value

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
                onBookmark = { job ->
                    bookmarkViewModel.onBookmarkToggle(job)
                },
                onNavItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                bookmarkJobIds = bookmarkedJobsId
            )
        }
        composable(Screen.JobDetailScreen.route) {
            selectedJob?.let {
                val isBookmarked = bookmarkedJobsId.contains(it.id)
                JobDetailsScreen(
                    job = it,
                    navigateBack = {
                        navController.popBackStack()
                    },

                    onBookmark = { job ->
                        bookmarkViewModel.onBookmarkToggle(job)
                    },
                    isBookmarked = isBookmarked
                )
            }
        }
        composable(Screen.Bookmarks.route) {
            BookmarksScreen(
                bookmarkedJobs = bookmarkedJobs.value,
                onNavItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onBookmark = { job ->
                    bookmarkViewModel.onBookmarkToggle(job)
                },
                navigateToJobDetail = { job ->
                    sharedViewModel.selectJob(job)
                    navController.navigate(Screen.JobDetailScreen.route)
                },
            )
        }
    }
}