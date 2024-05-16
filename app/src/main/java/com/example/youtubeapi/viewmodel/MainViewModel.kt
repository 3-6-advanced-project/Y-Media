package com.example.youtubeapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.youtubeapi.data.model.VideoResponse
import com.example.youtubeapi.data.repository.VideoRepository
import com.example.youtubeapi.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Represents different states for the LatestNews screen
sealed class LatestNewsUiState {
    data class Success(val videoResponse: VideoResponse?): LatestNewsUiState()
    data class Error(val exception: Throwable): LatestNewsUiState()
}

class MainViewModel(
    private val videoRepository: VideoRepository
): ViewModel() {
    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(LatestNewsUiState.Success(null))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    fun onSearch(
        query: String
    ) = viewModelScope.launch {
        runCatching {
            val items = videoRepository.getSearchVideo(
                query = query
            )

            _uiState.value = LatestNewsUiState.Success(items)

            Log.d("Api Call Success", items.toString())
        }.onFailure {
            Log.e("Api Call Error", it.message.toString())
        }
    }
}


class MainViewModelFactory : ViewModelProvider.Factory {

    private val repository = VideoRepository(
        RetrofitClient.develop,
        RetrofitClient.base
    )

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T = MainViewModel(repository) as T
}