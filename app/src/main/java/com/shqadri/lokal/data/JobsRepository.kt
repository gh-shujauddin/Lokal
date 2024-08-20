package com.shqadri.lokal.data

import com.shqadri.lokal.domain.Resource
import com.shqadri.lokal.domain.models.JobUIState
import kotlinx.coroutines.flow.Flow

interface JobsRepository {
    fun getJobs(): Flow<Resource<List<JobUIState>>>
    suspend fun insertBookmark(job: JobUIState)
    suspend fun deleteBookmark(job: JobUIState)
    fun getAllBookmarks(): Flow<List<JobUIState>>
}
