package com.shqadri.lokal.data

import com.shqadri.lokal.domain.Resource
import com.shqadri.lokal.network.JobsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsRepositoryImpl @Inject constructor(
    private val apiService: JobsApiService
) : JobsRepository {

    override fun getJobs() = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getJobs(page = 1)
            if (response.isSuccessful && response.body() != null) {
                val apiResponse = response.body()!!
                emit(Resource.Success(apiResponse))
            } else {
                emit(Resource.Error("Error fetching data"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
    }.flowOn(Dispatchers.IO)
}
