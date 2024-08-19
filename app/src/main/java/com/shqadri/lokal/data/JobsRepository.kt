package com.shqadri.lokal.data

import com.shqadri.lokal.model.ApiResponse
import com.shqadri.lokal.model.Resource
import kotlinx.coroutines.flow.Flow

interface JobsRepository {
    fun getJobs(): Flow<Resource<ApiResponse>>
}
