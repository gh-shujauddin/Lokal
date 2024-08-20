package com.shqadri.lokal.ui.screens.jobs

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shqadri.lokal.R
import com.shqadri.lokal.domain.models.Category
import com.shqadri.lokal.domain.models.Job
import com.shqadri.lokal.domain.models.JobUIState

//Top App bar with text and other icons
@Composable
fun AppTopAppBar(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
            Text(
                text = stringResource(R.string.lokal),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            BadgedBox(
                badge = {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription = "email",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

//List of categories  shown on homescreen
val categories = listOf(
    Category(1, "Technology", R.drawable.technology, Icons.Default.Lightbulb),
    Category(2, "Design", R.drawable.designing, Icons.Default.DesignServices),
    Category(3, "Healthcare", R.drawable.healthcare, Icons.Default.HealthAndSafety),
    Category(4, "Marketing", R.drawable.marketing, Icons.Default.Sell),
    Category(5, "Business", R.drawable.buisness, Icons.Default.Work),
)

//Card for a single category
@Composable
fun CategoryCard(modifier: Modifier = Modifier, category: Category) {

    Box(modifier = modifier.clip(MaterialTheme.shapes.medium)) {
        Image(
            painter = painterResource(id = category.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color.Black.copy(0.5f), blendMode = BlendMode.Darken),
            modifier = Modifier.fillMaxHeight()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.align(
                Alignment.Center
            )
        ) {
            Icon(
                imageVector = category.imageVector,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
        }
    }

}

/*
* Card for showing the details of a job
*/
@Composable
fun JobListingCard(
    job: JobUIState,
    onCallHrClick: () -> Unit,
    onCardClick: () -> Unit,
    onBookmark: () -> Unit,
    isBookmarked: Boolean
) {
    Card(
        onClick = onCardClick,
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = job.jobRole,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = "bookmark",
                    modifier = Modifier.clickable {
                        onBookmark()
                    },
                    tint = MaterialTheme.colorScheme.primary
                )

            }
            Text(
                text = job.salary,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Outlined.Business, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = job.companyName,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = job.place,
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
                            .border(
                                1.dp,
                                Color(android.graphics.Color.parseColor(tag.textColor)),
                                MaterialTheme.shapes.small
                            )
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
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.call_hr),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }


    }
}

//Error indicator
@Composable
fun ErrorBox(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.error
        )
    }
}

/*
* Loading indicator
*/
@Composable
fun LoadingBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
