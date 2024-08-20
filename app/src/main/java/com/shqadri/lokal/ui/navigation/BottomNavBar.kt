package com.shqadri.lokal.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavBar(currentRoute: String?, onNavItemClick: (String) -> Unit) {
    NavigationBar {
        screens.forEach { currentScreen ->
            val isSelected = currentRoute == currentScreen.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) currentScreen.selectedIcon else currentScreen.unselectedIcon,
                        contentDescription = "Jobs"
                    )
                },
                label = { Text(currentScreen.titleText) },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        onNavItemClick(currentScreen.route)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}