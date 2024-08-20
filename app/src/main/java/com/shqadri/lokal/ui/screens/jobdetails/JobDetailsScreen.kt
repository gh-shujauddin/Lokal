package com.shqadri.lokal.ui.screens.jobdetails

import android.content.Intent
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shqadri.lokal.R
import com.shqadri.lokal.domain.models.Job
import com.shqadri.lokal.domain.models.JobUIState
import com.shqadri.lokal.domain.models.PrimaryDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(
    job: JobUIState,
    navigateBack: () -> Unit,
    onBookmark: (JobUIState) -> Unit,
    isBookmarked: Boolean
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle share action */ }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = android.net.Uri.parse(job.customLink)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium
            ) {

                Text(text = job.buttonText)
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            HorizontalDivider(modifier = Modifier.shadow(2.dp))
            JobHeader(
                modifier = Modifier.padding(16.dp),
                title = job.jobRole,
                companyName = job.companyName,
                vacancy = job.openingsCount,
                applicantCount = job.numApplications.toString(),
                jobPostDate = job.updatedOn,
                salary = job.salary,
                place = job.place,
                jobType = job.jobType,
                experience = job.experience,
                qualification = job.qualification,
                isBookmarked = isBookmarked,
                onBookmark = { onBookmark(job) }
            )
            HorizontalDivider()
            JobDetailHeading(
                text = "Job description",
                modifier = Modifier.padding(top = 22.dp, start = 16.dp)
            )
            JobDescriptionContainer(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                jobDescription = job.title
            )
            JobDetailHeading(
                text = "Job details",
                modifier = Modifier.padding(start = 16.dp)
            )
            MoreJobDetailContainer(
                job = job,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            ViewsContainer(views = job.views.toString())
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider()
            Text(
                text = stringResource(R.string.job_disclaimer),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.primary.copy(0.8f)
            )
        }

    }

}
