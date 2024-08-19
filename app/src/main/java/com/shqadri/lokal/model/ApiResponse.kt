package com.shqadri.lokal.model

data class ApiResponse(
    val results: List<Job>
)

data class ContentV3(
    val V3: List<ContentV3Item>
)

data class ContentV3Item(
    val fieldKey: String,
    val fieldName: String,
    val fieldValue: String
)
