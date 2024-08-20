package com.shqadri.lokal.domain.mappers

import com.shqadri.lokal.domain.models.ContentV3ItemEntity
import com.shqadri.lokal.domain.models.ContentV3ItemUIState
import com.shqadri.lokal.domain.models.JobEntity
import com.shqadri.lokal.domain.models.JobTagEntity
import com.shqadri.lokal.domain.models.JobTagUIState
import com.shqadri.lokal.domain.models.JobUIState

fun JobUIState.toEntity(): JobEntity {
    return JobEntity(
        id = id ?: 0,
        title = title,
        place = place,
        salary = salary,
        jobType = jobType,
        experience = experience,
        qualification = qualification,
        companyName = companyName,
        buttonText = buttonText,
        customLink = customLink,
        views = views,
        updatedOn = updatedOn,
        whatsappNo = whatsappNo,
        whatsappLink = whatsappLink,
        jobHours = jobHours,
        openingsCount = openingsCount,
        jobRole = jobRole,
        otherDetails = otherDetails,
        jobCategory = jobCategory,
        numApplications = numApplications,
        jobTags = jobTags.map { it.toEntity() },
        contentV3 = contentV3.map { it.toEntity() }
    )
}

fun JobTagUIState.toEntity(): JobTagEntity {
    return JobTagEntity(
        value = value,
        bgColor = bgColor,
        textColor = textColor
    )
}

fun ContentV3ItemUIState.toEntity(): ContentV3ItemEntity {
    return ContentV3ItemEntity(
        fieldKey = fieldKey,
        fieldValue = fieldValue
    )
}
