package com.shqadri.lokal.ui.screens.bookmark

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shqadri.lokal.R
import com.shqadri.lokal.domain.models.JobUIState
import com.shqadri.lokal.ui.navigation.BottomNavBar
import com.shqadri.lokal.ui.navigation.Screen
import com.shqadri.lokal.ui.screens.jobs.JobListingCard

@Composable
fun BookmarksScreen(
    bookmarkedJobs: List<JobUIState>,
    onNavItemClick: (String) -> Unit,
    onBookmark: (JobUIState) -> Unit,
    navigateToJobDetail: (JobUIState) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = Screen.Bookmarks.route,
                onNavItemClick = onNavItemClick
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(R.string.bookmarked_jobs),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
            AnimatedVisibility(visible = bookmarkedJobs.isEmpty()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_saved_jobs),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.tap_on_bookmark_icon_against_a_job_to_save_it),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(bookmarkedJobs) { job ->

                    JobListingCard(
                        job = job,
                        onCallHrClick = {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = android.net.Uri.parse(job.customLink)
                            context.startActivity(intent)
                        },
                        onCardClick = {
                            navigateToJobDetail(job)
                        },
                        onBookmark = {
                            onBookmark(job)
                        },
                        isBookmarked = true
                    )
                }

            }

        }
    }
}