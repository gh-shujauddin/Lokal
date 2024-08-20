package com.shqadri.lokal.ui.screens.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shqadri.lokal.data.JobsRepository
import com.shqadri.lokal.domain.models.JobUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val jobsRepository: JobsRepository
) : ViewModel() {

    private val _bookmarkedJobIds = MutableStateFlow<List<Int>>(emptyList())
    val bookmarkedJobIds = _bookmarkedJobIds.asStateFlow()

    private val _bookmarkedJobs = MutableStateFlow<List<JobUIState>>(listOf())
    val bookmarkedJobs = _bookmarkedJobs.asStateFlow()

    init {
        loadBookmarkedJobs()
    }

    private fun loadBookmarkedJobs() {
        viewModelScope.launch {
            jobsRepository.getAllBookmarks().collect { bookmarks ->
                _bookmarkedJobIds.value = bookmarks.filter { it.id != null }.map { it.id!! }
                _bookmarkedJobs.value = bookmarks.filter { it.id != null }
            }
        }
    }

    fun onBookmarkToggle(job: JobUIState) {
        viewModelScope.launch {
            if (_bookmarkedJobIds.value.contains(job.id)) {
                jobsRepository.deleteBookmark(job)
            } else {
                jobsRepository.insertBookmark(job)
            }
            loadBookmarkedJobs()
        }
    }

}