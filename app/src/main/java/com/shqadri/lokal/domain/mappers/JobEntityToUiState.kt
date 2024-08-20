package com.shqadri.lokal.domain.mappers

import com.shqadri.lokal.domain.models.ContentV3ItemEntity
import com.shqadri.lokal.domain.models.ContentV3ItemUIState
import com.shqadri.lokal.domain.models.JobEntity
import com.shqadri.lokal.domain.models.JobTagEntity
import com.shqadri.lokal.domain.models.JobTagUIState
import com.shqadri.lokal.domain.models.JobUIState

fun JobEntity.toUIState(): JobUIState {
    return JobUIState(
        id = id,
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
        jobTags = jobTags.map { it.toUIState() },
        contentV3 = contentV3.map { it.toUIState() }
    )
}

fun JobTagEntity.toUIState(): JobTagUIState {
    return JobTagUIState(
        value = value,
        bgColor = bgColor,
        textColor = textColor
    )
}

fun ContentV3ItemEntity.toUIState(): ContentV3ItemUIState {
    return ContentV3ItemUIState(
        fieldKey = fieldKey,
        fieldValue = fieldValue
    )
}
