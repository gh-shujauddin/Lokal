package com.shqadri.lokal.ui.screens.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shqadri.lokal.data.JobsRepository
import com.shqadri.lokal.model.ApiResponse
import com.shqadri.lokal.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val repository: JobsRepository
) : ViewModel() {

    private val _jobsUiState = MutableStateFlow<Resource<ApiResponse>>(Resource.Loading())
    val jobsUiState: StateFlow<Resource<ApiResponse>> = _jobsUiState.asStateFlow()

    init {
        fetchJobs()
    }

    fun fetchJobs() =
        viewModelScope.launch {
            repository.getJobs().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            _jobsUiState.value = Resource.Success(it)
                        }
                    }

                    is Resource.Error -> {
                        _jobsUiState.value = Resource.Error(resource.message ?: "Unexpected Error")
                    }

                    is Resource.Loading -> {
                        _jobsUiState.value = Resource.Loading()
                    }
                }
            }
        }
}