package com.shqadri.lokal.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector, val titleText: String) {
    object Jobs : Screen("jobs", Icons.Filled.Work, Icons.Outlined.Work, "Jobs")
    object Bookmarks : Screen("bookmarks", Icons.Filled.Bookmarks, Icons.Outlined.Bookmarks, "Bookmarks")
}

val screens = listOf(
    Screen.Jobs,
    Screen.Bookmarks
)
