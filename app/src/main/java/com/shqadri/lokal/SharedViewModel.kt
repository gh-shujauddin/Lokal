package com.shqadri.lokal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shqadri.lokal.data.JobsRepository
import com.shqadri.lokal.domain.models.Job
import com.shqadri.lokal.domain.models.JobUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _selectedJob = MutableStateFlow<JobUIState?>(null)
    val selectedJob: StateFlow<JobUIState?> = _selectedJob.asStateFlow()

    fun selectJob(job: JobUIState) {
        _selectedJob.value = job
    }
}
