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
    private val videoRepository: VideoRepository,
    private val videoChannel: Channel
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

    //디테일 페이지로 이동 시 Search 결과 연결 + 0번으로만 이동해서 분리.
    private val _uiDetailState: MutableStateFlow<LatestNewsUiState> = MutableStateFlow(LatestNewsUiState.Success(emptyList()))
    val uiDetailState = _uiDetailState.asStateFlow()

    private val _bookmarks = MutableStateFlow(listOf<VideoEntity>())
    val bookmarks = _bookmarks.asStateFlow()

    init {
        initMostPopularVideos()
        initVideoCategories()
        initBookmarks()
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

    private fun initBookmarks() {
        viewModelScope.launch {
            videoEntityDao.getAllVideoEntity().collect {
                Log.e("URGENT_TAG", "initBookmarks: called!")
                val temp = it.toList()
                _bookmarks.value = temp
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

    // Gson converter = dto 만들어서 class로 변환...
    fun onDetail(videoId: String) = viewModelScope.launch{
        runCatching {
            val videos = videoRepository.getVideoById(videoId = videoId) //여기까진 해당 videos list(항 1개) 들고옴 이해함
            Log.d("test 1", "1")
            val videoState = videos.items.map { it.asVideoState() } //이 코드는 뭐 하는 거지? 각 비디오 (지금은 1개)내 정렬?
            Log.d("test 2", "2")
            _uiDetailState.value = LatestNewsUiState.Success(videoState) //이해못함 > _uiDetailState값을 ui 띄울때 불러와야됨
            Log.d("Api Call Success", videoState.toString())
        }.onFailure {
            _uiDetailState.value = LatestNewsUiState.Error(it)
            Log.e("Api Call Error", it.message.toString())

        }
    }

    fun onDetailChannels(channelId: String) = viewModelScope.launch{
        runCatching {
            val channel = videoRepository.getChannelById(channelId = channelId)
            val channelState = channel.items.map {it.asChannelState()}
            _uiDetailState.value = LatestNewsUiState.Success(channelState)
            Log.d("Api Call Success", channelState.toString())
        }.onFailure { 
            _uiDetailState.value = LatestNewsUiState.Error(it)
            Log.e("Api Call Error", it.message.toString())
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
