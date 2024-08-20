package com.shqadri.lokal.ui.screens.jobdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shqadri.lokal.domain.models.PrimaryDetails
import com.shqadri.lokal.utils.formatDate

@Composable
fun JobHeader(
    modifier: Modifier = Modifier,
    title: String,
    companyName: String,
    applicantCount: String,
    jobPostDate: String,
    vacancy: Int,
    jobDetails: PrimaryDetails,
    onBookmark: () -> Unit
) {
    Column(modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Outlined.BookmarkBorder,
                contentDescription = "bookmark",
                modifier = Modifier.clickable {
                    onBookmark()
                }
            )

        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = jobDetails.Salary,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        JobHeaderItem(icon = Icons.Outlined.LocationOn, text = jobDetails.Place)
        Spacer(modifier = Modifier.height(12.dp))
        JobHeaderItem(icon = Icons.Outlined.Business, text = companyName)
        Spacer(modifier = Modifier.height(12.dp))
        JobHeaderItem(icon = Icons.Outlined.Group, text = "$vacancy vacancies")
        Spacer(modifier = Modifier.height(20.dp))
        JobDetailContainer(jobDetails = jobDetails)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$applicantCount applicants",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary.copy(0.5f)
            )
            Text(
                text = "Posted on ${jobPostDate.formatDate()}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary.copy(0.5f)
            )
        }
    }
}

@Composable
fun JobHeaderItem(modifier: Modifier = Modifier, icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}