package com.shqadri.lokal.ui.screens.jobs

import android.content.Intent
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shqadri.lokal.R
import com.shqadri.lokal.model.ApiResponse
import com.shqadri.lokal.model.Job
import com.shqadri.lokal.model.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen(jobsUiState: Resource<ApiResponse>) {
    val context = LocalContext.current

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
                    text = "Actively Hiring",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
            }
            when (jobsUiState) {
                is Resource.Error -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = jobsUiState.message ?: "Unexpected error occured",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }

                is Resource.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is Resource.Success -> {
                    val jobs = jobsUiState.data?.results!!

                    items(jobs) { job ->
                        Log.d("JOBS", job.toString())
                        if (job.id == null) return@items
                        JobListingCard(
                            job = job,
                            onCallHrClick = {
                                val intent = Intent(Intent.ACTION_DIAL)
                                intent.data = android.net.Uri.parse(job.customLink)
                                context.startActivity(intent)
                            },
                            onCardClick = {
                                // TODO
                            }
                        )
                    }

                }

            }
        }

    }
}


@Composable
fun JobListingCard(
    job: Job,
    onCallHrClick: () -> Unit,
    onCardClick: () -> Unit
) {
    Card(
        onClick = onCardClick,
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                ExpandableText(
                    text = job.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                )

                Icon(imageVector = Icons.Outlined.BookmarkBorder, contentDescription = "bookmark")
            }
            Text(
                text = job.primaryDetails.Salary,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = job.companyName,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = job.primaryDetails.Place,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                job.jobTags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(Color(android.graphics.Color.parseColor(tag.bgColor)))
                    ) {
                        Text(
                            text = tag.value,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            color = Color(android.graphics.Color.parseColor(tag.textColor))
                        )
                    }
                }
            }

            Button(
                onClick = onCallHrClick,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {

                Text(text = job.buttonText)
            }
        }


    }
}


@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    text: String,
    collapsedMaxLine: Int = 2,
    showMoreText: String = "... ",
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.W500),
    showLessText: String = "",
    showLessStyle: SpanStyle = showMoreStyle,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified
) {
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableStateOf(0) }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Box(modifier = Modifier
        .clickable(interactionSource, indication = null) {
            isExpanded = !isExpanded
        }
        .then(modifier)
    ) {
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .animateContentSize(),
            text = buildAnnotatedString {
                if (clickable) {
                    if (isExpanded) {
                        append(text)
                        withStyle(style = showLessStyle) { append(showLessText) }
                    } else {
                        val adjustText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                        append(adjustText)
                        withStyle(style = showMoreStyle) { append(showMoreText) }
                    }
                } else {
                    append(text)
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    clickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                }
            },
            style = style,
            textAlign = textAlign,
            color = color
        )
    }

}


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
