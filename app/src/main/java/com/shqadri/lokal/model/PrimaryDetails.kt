package com.shqadri.lokal.model

import com.google.gson.annotations.SerializedName

data class PrimaryDetails(
    val Place: String,
    @SerializedName("Salary")
    val Salary: String,
    val Job_Type: String,
    val Experience: String,
    val Fees_Charged: String,
    val Qualification: String
)