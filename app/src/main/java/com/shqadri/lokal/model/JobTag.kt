package com.shqadri.lokal.model

import com.google.gson.annotations.SerializedName

data class JobTag(
    val value: String,
    @SerializedName("bg_color")
    val bgColor: String,
    @SerializedName("text_color")
    val textColor: String
)