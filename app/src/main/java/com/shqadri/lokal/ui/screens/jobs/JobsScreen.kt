package com.shqadri.lokal.ui.screens.jobs

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shqadri.lokal.R
import com.shqadri.lokal.domain.models.ApiResponse
import com.shqadri.lokal.domain.models.Job
import com.shqadri.lokal.domain.Resource
import com.shqadri.lokal.domain.models.JobUIState
import com.shqadri.lokal.ui.navigation.BottomNavBar
import com.shqadri.lokal.ui.navigation.Screen

@Composable
fun JobsScreen(
    jobsUiState: Resource<List<JobUIState>>,
    navigateToJobDetail: (JobUIState) -> Unit,
    onBookmark: (JobUIState) -> Unit,
    onNavItemClick: (String) -> Unit,
    bookmarkJobIds: List<Int>
) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = Screen.Jobs.route,
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
                AppTopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        AppHeader()
                        Text(
                            text = stringResource(R.string.actively_hiring),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    when (jobsUiState) {
                        is Resource.Error -> {
                            item {
                                ErrorBox(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    text = stringResource(R.string.please_check_you_connection)
                                )
                            }
                        }

                        is Resource.Loading -> {
                            item {
                                LoadingBox(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                )
                            }
                        }

                        is Resource.Success -> {
                            val jobs = jobsUiState.data!!

                            items(jobs) { job ->
                                //Showing each jobs from API
                                if (job.id == null) return@items
                                val isBookmarked by rememberUpdatedState(
                                    newValue = bookmarkJobIds.contains(
                                        job.id
                                    )
                                )

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
                                    isBookmarked = isBookmarked
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
* App header with categories and other texts
*/
@Composable
fun AppHeader() {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.your_ideal_job_here),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W600)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                CategoryCard(modifier = Modifier.size(88.dp), category = category)
            }
        }
    }
}
