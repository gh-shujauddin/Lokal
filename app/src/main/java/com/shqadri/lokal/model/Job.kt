package com.shqadri.lokal.model

import com.google.gson.annotations.SerializedName

data class Job(
    val id: Int?,
    val title: String,
    val type: Int,
    @SerializedName("primary_details")
    val primaryDetails: PrimaryDetails,
    @SerializedName("job_tags")
    val jobTags: List<JobTag>,
    val jobType: Int,
    val jobCategoryId: Int,
    val qualification: Int,
    val experience: Int,
    val shiftTiming: Int,
    val jobRoleId: Int,
    val salaryMax: Int?,
    val salaryMin: Int?,
    val cityLocation: Int,
    val locality: Int,
    val premiumTill: String?,
    val content: String,
    @SerializedName("company_name")
    val companyName: String,
    val advertiser: Int,
    @SerializedName("button_text")
    val buttonText: String,
    @SerializedName("custom_link")
    val customLink: String,
    val amount: String,
    val views: Int,
    val shares: Int,
    val fbShares: Int,
    val isBookmarked: Boolean,
    val isApplied: Boolean,
    val isOwner: Boolean,
    val updatedOn: String,
    val whatsappNo: String,
    val contactPreference: ContactPreference,
    val createdOn: String,
    val isPremium: Boolean,
    val creatives: List<Creative>,
    val locations: List<Location>,
    val contentV3: ContentV3,
    val status: Int,
    val expireOn: String,
    val jobHours: String,
    val openingsCount: Int,
    val jobRole: String,
    val otherDetails: String,
    val jobCategory: String,
    val numApplications: Int,
    val enableLeadCollection: Boolean,
    val isJobSeekerProfileMandatory: Boolean,
    @SerializedName("job_location_slug")
    val jobLocationSlug: String,
    val feesText: String,
    val shouldShowLastContacted: Boolean,
    val feesCharged: Int
)
