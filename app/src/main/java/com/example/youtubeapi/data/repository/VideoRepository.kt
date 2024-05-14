package com.example.youtubeapi.data.repository

import androidx.annotation.IntRange
import com.example.youtubeapi.data.remote.RemoteDataSource

class VideoRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getMostPopularVideo(
        @IntRange(from = 72, to = 8192) maxHeight: Int = 512,
        @IntRange(from = 1, to = 50) maxResults: Int = 5,
        @IntRange(from = 72, to = 8192) maxWidth: Int = 512,
    ) = remoteDataSource.getMostPopularVideo(
        maxHeight = maxHeight,
        maxResults = maxResults,
        maxWidth = maxWidth
    )
}