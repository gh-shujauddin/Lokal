package com.shqadri.lokal.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

const val BASE_URL = "https://testapi.getlokalapp.com/common/"

fun String.formatDate(): String {
    val inputFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())

    return try {
        val dateTime = OffsetDateTime.parse(this, inputFormatter)
        dateTime.format(outputFormatter)
    } catch (e: Exception) {
        "Invalid Date Format"
    }
}