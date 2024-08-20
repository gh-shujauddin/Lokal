package com.shqadri.lokal.data

import com.shqadri.lokal.domain.Resource
import com.shqadri.lokal.domain.mappers.toEntity
import com.shqadri.lokal.domain.mappers.toUIState
import com.shqadri.lokal.domain.models.JobUIState
import com.shqadri.lokal.network.JobsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsRepositoryImpl @Inject constructor(
    private val apiService: JobsApiService,
    private val bookmarkDao: BookmarkDao
) : JobsRepository {

    override fun getJobs() = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getJobs(page = 1)
            if (response.isSuccessful && response.body() != null) {
                val apiResponse = response.body()!!.results

                // Mapping the API response to a list of JobUIState
                val jobsUIState = apiResponse
                    .filter { it.id != null }
                    .map { item ->
                        item.toUIState()
                    }

                emit(Resource.Success(jobsUIState))
            } else {
                emit(Resource.Error("Error fetching data"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
    }.flowOn(Dispatchers.IO)

    override suspend fun insertBookmark(job: JobUIState) {
        val jobEntity = job.toEntity()
        bookmarkDao.insertBookmark(jobEntity)
    }

    override suspend fun deleteBookmark(job: JobUIState) {
        val jobEntity = job.toEntity()
        bookmarkDao.deleteBookmark(jobEntity)
    }

    override fun getAllBookmarks(): Flow<List<JobUIState>> =
        bookmarkDao.getAllBookmarks().map { bookmarks ->
            bookmarks.map {
                it.toUIState()
            }
        }


}

