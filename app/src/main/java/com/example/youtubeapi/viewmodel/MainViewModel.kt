package com.example.youtubeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.model.dto.VideoCategory
import com.example.youtubeapi.data.repository.VideoRepository
import com.example.youtubeapi.network.RetrofitClient
import com.example.youtubeapi.presentation.uistate.VideoState
import com.example.youtubeapi.presentation.uistate.asVideoState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val videoRepository: VideoRepository
): ViewModel() {

    private val _mostPopularVideos = MutableStateFlow(listOf<VideoState>())
    val mostPopularVideos = _mostPopularVideos.asStateFlow()

    private val _mostPopularVideosWithCategory = MutableStateFlow(listOf<VideoState>())
    val mostPopularVideoWithCategory = _mostPopularVideosWithCategory.asStateFlow()

    private val _channels = MutableStateFlow(listOf<String>())
    val channels = _channels.asStateFlow()

    private val _categories = MutableStateFlow(listOf<VideoCategory>())
    val categories = _categories.asStateFlow()

    private val _currentCategory = MutableStateFlow<VideoCategory?>(null)
    val currentCategory = _currentCategory.asStateFlow()

    init {
        initMostPopularVideos()
        initVideoCategories()
    }

    private fun initMostPopularVideos() {
        viewModelScope.launch(Dispatchers.IO) {
            _mostPopularVideos.value = videoRepository.getMostPopularVideo().items.map {
                it.asVideoState()
            }
        }
    }

    private fun initVideoCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categories.value = videoRepository.getVideoCategories().items
            initCurrentCategory(_categories.value[0])
        }
    }

    private fun initCurrentCategory(videoCategory: VideoCategory) {
        _currentCategory.value = videoCategory
        viewModelScope.launch(Dispatchers.IO) {
            videoRepository.getMostPopularVideo(videoCategoryId = videoCategory.id)
        }
    }
}

class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            VideoRepository(RetrofitClient.googleApiSource)
        ) as T
    }
}

