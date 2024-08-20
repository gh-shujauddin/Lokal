package com.shqadri.lokal.data

import com.shqadri.lokal.domain.models.ApiResponse
import com.shqadri.lokal.domain.Resource
import kotlinx.coroutines.flow.Flow

interface JobsRepository {
    fun getJobs(): Flow<Resource<ApiResponse>>
}
