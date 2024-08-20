package com.shqadri.lokal.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactPreference(
    val whatsappLink: String,
): Parcelable