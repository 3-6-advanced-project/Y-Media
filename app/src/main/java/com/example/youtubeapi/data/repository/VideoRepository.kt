package com.example.youtubeapi.data.repository

import androidx.annotation.IntRange
import com.example.youtubeapi.data.remote.DeveloperDataSource
import com.example.youtubeapi.data.remote.GoogleApiDataSource

class VideoRepository(
    private val developerDataSource: DeveloperDataSource,
    private val googleApiDataSource: GoogleApiDataSource,
) {
    suspend fun getMostPopularVideo(
        @IntRange(from = 72, to = 8192) maxHeight: Int = 512,
        @IntRange(from = 1, to = 50) maxResults: Int = 50,
        @IntRange(from = 72, to = 8192) maxWidth: Int = 512,
        videoCategoryId: String = "0",
    ) = googleApiDataSource.getMostPopularVideo(
        maxHeight = maxHeight,
        maxResults = maxResults,
        maxWidth = maxWidth,
        videoCategoryId = videoCategoryId
    )
    suspend fun getVideoCategories() = googleApiDataSource.getVideoCategories()

    /**
     * @param idListString channel id를 ,로 연결한 string
     * */
    suspend fun getChannels(idListString: String) =
        googleApiDataSource.getChannels(id = idListString)

    suspend fun getSearchVideo(
        query: String,
        videoDuration: String = "any",
        @IntRange(from = 1, to = 50) maxResults: Int = 50,
        videoDefinition: String = "high",
    ) = googleApiDataSource.getSearchVideo(
        query = query,
        videoDuration = videoDuration,
        maxResults = maxResults,
        videoDefinition = videoDefinition,
    )

    suspend fun getVideoById(
        videoId: String,
    ) = googleApiDataSource.getVideoById(
        id = videoId
    )

    suspend fun getByChannelId(
        channelId: String,
    ) = googleApiDataSource.getByChannelId(
        id = channelId
    )
}
