package com.shqadri.lokal.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Job(
    val id: Int?,
    val title: String,
    @SerializedName("primary_details")
    val primaryDetails: PrimaryDetails,
    @SerializedName("job_tags")
    val jobTags: List<JobTag>,
    @SerializedName("company_name")
    val companyName: String,
    @SerializedName("button_text")
    val buttonText: String,
    @SerializedName("custom_link")
    val customLink: String,
    val views: Int,
    @SerializedName("updated_on")
    val updatedOn: String,
    @SerializedName("whatsapp_no")
    val whatsappNo: String,
    @SerializedName("contact_preference")
    val contactPreference: ContactPreference,
    val createdOn: String,
    val contentV3: ContentV3,
    @SerializedName("job_hours")
    val jobHours: String,
    @SerializedName("openings_count")
    val openingsCount: Int,
    @SerializedName("job_role")
    val jobRole: String,
    @SerializedName("other_details")
    val otherDetails: String,
    @SerializedName("job_category")
    val jobCategory: String,
    @SerializedName("num_applications")
    val numApplications: Int,
) : Parcelable
