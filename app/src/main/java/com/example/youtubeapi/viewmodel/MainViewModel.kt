package com.example.youtubeapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.youtubeapi.data.repository.VideoRepository
import com.example.youtubeapi.network.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel(
    private val videoRepository: VideoRepository
): ViewModel() {

    fun onSearch(
        query: String
    ) = viewModelScope.launch {
        runCatching {
            val items = videoRepository.getSearchVideo(
                query = query
            )

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