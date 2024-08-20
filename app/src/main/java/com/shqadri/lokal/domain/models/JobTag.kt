package com.shqadri.lokal.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobTag(
    val value: String,
    @SerializedName("bg_color")
    val bgColor: String,
    @SerializedName("text_color")
    val textColor: String
): Parcelable