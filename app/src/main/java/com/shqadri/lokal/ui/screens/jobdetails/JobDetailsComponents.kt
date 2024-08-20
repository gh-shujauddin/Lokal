package com.shqadri.lokal.ui.screens.jobdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shqadri.lokal.domain.models.JobUIState


@Composable
fun JobDetailContainer(
    modifier: Modifier = Modifier,
    jobType: String,
    experience: String,
    qualification: String
) {
    Row(modifier = modifier.fillMaxWidth()) {
        JobDetail(
            icon = Icons.Default.Work,
            title = "Job type",
            text = jobType,
            modifier = Modifier.weight(1f)

        )
        JobDetail(
            icon = Icons.Default.History,
            title = "Experience",
            text = experience,
            modifier = Modifier.weight(1f)

        )
        JobDetail(
            icon = Icons.Default.MenuBook,
            title = "Qualification",
            text = qualification,
            modifier = Modifier.weight(1f)

        )
    }
}


@Composable
fun JobDescriptionContainer(jobDescription: String, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = jobDescription)
    }

}

@Composable
fun ViewsContainer(modifier: Modifier = Modifier, views: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.RemoveRedEye,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "${views} views",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun JobDetailHeading(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
        modifier = modifier
    )
}

@Composable
fun MoreJobDetailContainer(job: JobUIState, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column {
                Text(
                    text = "Job hours",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary.copy(0.8f)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = job.jobHours,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            job.contentV3.forEach { content ->
                if (content.fieldValue.isEmpty()) return@forEach
                Column {
                    Text(
                        text = content.fieldKey,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary.copy(0.8f)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = content.fieldValue,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Composable
fun JobDetail(modifier: Modifier = Modifier, icon: ImageVector, title: String, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.primary.copy(0.2f))
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center

        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary.copy(0.8f),
            textAlign = TextAlign.Center
        )
    }
}