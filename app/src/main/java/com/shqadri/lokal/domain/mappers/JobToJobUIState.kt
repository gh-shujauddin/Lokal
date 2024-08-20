package com.shqadri.lokal.domain.mappers

import com.shqadri.lokal.domain.models.ContentV3Item
import com.shqadri.lokal.domain.models.ContentV3ItemUIState
import com.shqadri.lokal.domain.models.Job
import com.shqadri.lokal.domain.models.JobTag
import com.shqadri.lokal.domain.models.JobTagUIState
import com.shqadri.lokal.domain.models.JobUIState
import com.shqadri.lokal.utils.formatDate

fun Job.toUIState(): JobUIState {
    return JobUIState(
        id = id,
        title = title,
        place = primaryDetails.Place,
        salary = primaryDetails.Salary,
        jobType = primaryDetails.Job_Type,
        experience = primaryDetails.Experience,
        qualification = primaryDetails.Qualification,
        companyName = companyName,
        buttonText = buttonText,
        customLink = customLink,
        views = views,
        updatedOn = updatedOn.formatDate(),
        whatsappNo = whatsappNo,
        whatsappLink = contactPreference.whatsappLink,
        jobHours = jobHours,
        openingsCount = openingsCount,
        jobRole = jobRole,
        otherDetails = otherDetails,
        jobCategory = jobCategory,
        numApplications = numApplications,
        jobTags = jobTags.map {
            it.toUIState()
        },
        contentV3 = contentV3.V3.map { it.toUIState() }
    )
}

fun JobTag.toUIState(): JobTagUIState {
    return JobTagUIState(
        value = value,
        bgColor = bgColor,
        textColor = textColor
    )
}

fun ContentV3Item.toUIState(): ContentV3ItemUIState {
    return ContentV3ItemUIState(
        fieldKey = fieldKey,
        fieldValue = fieldValue
    )
}
