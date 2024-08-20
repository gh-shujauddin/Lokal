package com.shqadri.lokal.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val id: Int,
    val title: String,
    val image: Int,
    val imageVector: ImageVector
)