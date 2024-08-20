package com.shqadri.lokal.domain.models

data class JobUIState(
    val id: Int?,
    val title: String,
    val place: String,
    val salary: String,
    val jobType: String,
    val experience: String,
    val qualification: String,
    val companyName: String,
    val buttonText: String,
    val customLink: String,
    val views: Int,
    val updatedOn: String,
    val whatsappNo: String,
    val whatsappLink: String,
    val jobHours: String,
    val openingsCount: Int,
    val jobRole: String,
    val otherDetails: String,
    val jobCategory: String,
    val numApplications: Int,
    val jobTags: List<JobTagUIState>,
    val contentV3: List<ContentV3ItemUIState>
)

data class JobTagUIState(
    val value: String,
    val bgColor: String,
    val textColor: String
)

data class ContentV3ItemUIState(
    val fieldKey: String,
    val fieldValue: String
)
