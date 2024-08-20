package com.shqadri.lokal.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PrimaryDetails(
    @SerializedName("Place")
    val Place: String,
    @SerializedName("Salary")
    val Salary: String,
    @SerializedName("Job_Type")
    val Job_Type: String,
    @SerializedName("Experience")
    val Experience: String,
    @SerializedName("Qualification")
    val Qualification: String
) : Parcelable