package com.shqadri.lokal.model

sealed class Screen(val route: String) {
    object Jobs : Screen("jobs")
    object Bookmarks : Screen("bookmarks")
}
