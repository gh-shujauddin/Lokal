package com.shqadri.lokal.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ApiResponse(
    val results: List<Job>
)

@Parcelize
data class ContentV3(
    val V3: List<ContentV3Item>
) : Parcelable

@Parcelize
data class ContentV3Item(
    @SerializedName("field_key")
    val fieldKey: String,
    @SerializedName("field_value")
    val fieldValue: String
) : Parcelable
