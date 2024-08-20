package com.shqadri.lokal.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactPreference(
    @SerializedName("whatsapp_link")
    val whatsappLink: String,
): Parcelable