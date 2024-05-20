package com.example.youtubeapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.model.dto.Channel
import com.example.youtubeapi.data.model.dto.VideoCategory
import com.example.youtubeapi.data.repository.VideoRepository
import com.example.youtubeapi.extractChannelIdStringFromVideos
import com.example.youtubeapi.network.RetrofitClient
import com.example.youtubeapi.presentation.uistate.VideoState
import com.example.youtubeapi.presentation.uistate.asVideoState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.local.dao.VideoEntityDao
import com.example.youtubeapi.data.model.entity.VideoEntity
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.youtubeapi.presentation.uistate.asVideoState

// Represents different states for the LatestNews screen
sealed interface LatestNewsUiState {
    data class Success(val videoStates: List<VideoState>) : LatestNewsUiState
    data class Error(val exception: Throwable) : LatestNewsUiState
}

class MainViewModel(
    private val videoEntityDao: VideoEntityDao,
    private val videoRepository: VideoRepository
): ViewModel() {

    private val _mostPopularVideos = MutableStateFlow(listOf<VideoState>())
    val mostPopularVideos = _mostPopularVideos.asStateFlow()

    private val _mostPopularVideosWithCategory = MutableStateFlow(listOf<VideoState>())
    val mostPopularVideoWithCategory = _mostPopularVideosWithCategory.asStateFlow()

    private val _channels = MutableStateFlow(listOf<Channel>())
    val channels = _channels.asStateFlow()

    private val _categories = MutableStateFlow(listOf<VideoCategory>())
    val categories = _categories.asStateFlow()

    private val _currentCategory = MutableStateFlow<VideoCategory?>(null)
    val currentCategory = _currentCategory.asStateFlow()

    private val _uiState: MutableStateFlow<LatestNewsUiState> = MutableStateFlow(LatestNewsUiState.Success(emptyList()))
    val uiState = _uiState.asStateFlow()

    private val _bookmarks = MutableStateFlow(listOf<VideoEntity>())
    val bookmarks = _bookmarks.asStateFlow()

    init {
        initMostPopularVideos()
        initVideoCategories()
        initBookmarks()
    }

    fun onSearch(
        query: String,
        videoDuration: String
    ) = viewModelScope.launch {
        runCatching {
            val videos = videoRepository.getSearchVideo(
                query = query,
                videoDuration = videoDuration
            )

            val videoState = videos.items.map {
                it.asVideoState()
            }

            _uiState.value = LatestNewsUiState.Success(videoState)

            Log.d("Api Call Success", videoState.toString())
        }.onFailure {
            _uiState.value = LatestNewsUiState.Error(it)

            Log.e("Api Call Error", it.message.toString())
        }
    }

    private fun initMostPopularVideos() {
        viewModelScope.launch(Dispatchers.IO) {
            _mostPopularVideos.value = videoRepository.getMostPopularVideo().items.map {
                Log.e("TAG", "initMostPopularVideos: $it", )
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

    private fun initBookmarks() {
        viewModelScope.launch {
            videoEntityDao.getAllVideoEntity().collect {
                _bookmarks.value = it
            }
        }
    }

    fun initCurrentCategory(videoCategory: VideoCategory) {
        _currentCategory.value = videoCategory

        viewModelScope.launch(Dispatchers.IO) {
            val temp = videoRepository
                .getMostPopularVideo(videoCategoryId = videoCategory.id).items

            _mostPopularVideosWithCategory.value = temp.map { it.asVideoState() }
            _channels.value = videoRepository
                .getChannels(extractChannelIdStringFromVideos(temp)).items
        }
    }
}


class MainViewModelFactory(
    private val videoEntityDao: VideoEntityDao
) : ViewModelProvider.Factory {

    private val repository = VideoRepository(
        RetrofitClient.developerApiSource,
        RetrofitClient.googleApiSource
    )

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T = MainViewModel(
        videoEntityDao,
        repository
    ) as T
}

