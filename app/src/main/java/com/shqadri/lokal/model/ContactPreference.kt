package com.shqadri.lokal.model

data class ContactPreference(
    val preference: Int,
    val whatsappLink: String,
    val preferredCallStartTime: String,
    val preferredCallEndTime: String
)