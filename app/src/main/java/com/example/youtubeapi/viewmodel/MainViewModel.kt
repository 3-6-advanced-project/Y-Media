package com.example.youtubeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.model.dto.Video
import com.example.youtubeapi.data.repository.VideoRepository
import com.example.youtubeapi.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val videoRepository: VideoRepository
): ViewModel() {

    private val _mostPopularVideos = MutableStateFlow(listOf<Video>())
    val mostPopularVideos = _mostPopularVideos.asStateFlow()

    private val _mostPopularVideosWithCategory = MutableStateFlow(listOf<Video>())
    val mostPopularVideoWithCategory = _mostPopularVideosWithCategory.asStateFlow()

    private val _channels = MutableStateFlow(listOf<String>())
    val channels = _channels.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _mostPopularVideos.value = videoRepository.getMostPopularVideo().items
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
