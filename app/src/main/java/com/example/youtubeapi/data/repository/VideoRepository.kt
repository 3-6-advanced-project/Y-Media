package com.example.youtubeapi.data.repository

import androidx.annotation.IntRange
import com.example.youtubeapi.data.remote.RemoteDataSource
import com.example.youtubeapi.data.remote.SearchDataSource

class VideoRepository(
    private val remoteDataSource: RemoteDataSource,
    private val searchDataSource: SearchDataSource
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

    /**
     * videoDefinition
     *    any - 해상도에 관계없이 모든 동영상을 반환합니다.
     *    high – HD 동영상만 검색합니다.
     *    standard – 표준 화질 동영상만 검색합니다.
     */
    suspend fun getSearchVideo(
        query: String,
        @IntRange(from = 1, to = 50) maxResults: Int = 2,
        videoDefinition: String = "high",
    ) = searchDataSource.getSearchVideo(
        query = query,
        maxResults = maxResults,
        videoDefinition = videoDefinition,
    )
}

