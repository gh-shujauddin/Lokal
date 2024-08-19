package com.shqadri.lokal.network

import com.shqadri.lokal.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApiService {
    @GET("jobs")
    suspend fun getJobs(
        @Query("page") page: Int,
    ): Response<ApiResponse>
}
