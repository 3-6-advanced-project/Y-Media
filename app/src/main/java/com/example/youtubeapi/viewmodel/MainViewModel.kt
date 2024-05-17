package com.example.youtubeapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.local.dao.VideoEntityDao
import com.example.youtubeapi.data.model.entity.VideoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.youtubeapi.data.model.dto.VideoResponse
import com.example.youtubeapi.data.repository.VideoRepository
import com.example.youtubeapi.network.RetrofitClient
import com.example.youtubeapi.presentation.uistate.VideoState
import com.example.youtubeapi.presentation.uistate.asVideoState
import kotlinx.coroutines.flow.StateFlow


// Represents different states for the LatestNews screen
sealed interface LatestNewsUiState {
    data class Success(val videoStates: List<VideoState>) : LatestNewsUiState
    data class Error(val exception: Throwable) : LatestNewsUiState
}

class MainViewModel(
    private val videoEntityDao: VideoEntityDao,
    private val videoRepository: VideoRepository,
) : ViewModel() {
    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<LatestNewsUiState> =
        MutableStateFlow(LatestNewsUiState.Success(emptyList()))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<LatestNewsUiState> = _uiState

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

    fun onSearch(
        query: String,
    ) = viewModelScope.launch {
        runCatching {
            val videos = videoRepository.getSearchVideo(
                query = query
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
