package com.shqadri.lokal.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_jobs")
data class JobEntity(
    @PrimaryKey val id: Int,
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
    val jobTags: List<JobTagEntity>,
    val contentV3: List<ContentV3ItemEntity>
)

data class JobTagEntity(
    val value: String,
    val bgColor: String,
    val textColor: String
)

data class ContentV3ItemEntity(
    val fieldKey: String,
    val fieldValue: String
)
