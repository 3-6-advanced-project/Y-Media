package com.example.youtubeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.local.dao.VideoEntityDao
import com.example.youtubeapi.data.model.entity.VideoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val videoEntityDao: VideoEntityDao
): ViewModel() {

    private val _bookmarks = MutableStateFlow(listOf<VideoEntity>())
    val bookmarks = _bookmarks.asStateFlow()

    init {
        initBookmarks()
    }

    private fun initBookmarks() {
        viewModelScope.launch {
            videoEntityDao.getAllVideoEntity().collect {
                _bookmarks.value = it
            }
        }
    }
}

class MainViewModelFactory(
    private val videoEntityDao: VideoEntityDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(videoEntityDao) as T
    }
}