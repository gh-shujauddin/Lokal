package com.shqadri.lokal.ui.screens.jobs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shqadri.lokal.R
import com.shqadri.lokal.model.Category

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

val categories = listOf(
    Category(1, "Technology", R.drawable.technology, Icons.Default.Lightbulb),
    Category(2, "Design", R.drawable.designing, Icons.Default.DesignServices),
    Category(3, "Healthcare", R.drawable.healthcare, Icons.Default.HealthAndSafety),
    Category(4, "Marketing", R.drawable.marketing, Icons.Default.Sell),
    Category(5, "Business", R.drawable.buisness, Icons.Default.Work),
)


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
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }

}