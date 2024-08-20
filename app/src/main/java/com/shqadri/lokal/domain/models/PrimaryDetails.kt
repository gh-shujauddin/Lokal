package com.shqadri.lokal.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PrimaryDetails(
    val Place: String,
    @SerializedName("Salary")
    val Salary: String,
    val Job_Type: String,
    val Experience: String,
    val Qualification: String
) : Parcelable