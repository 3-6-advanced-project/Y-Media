package com.example.youtubeapi.data.repository

import androidx.annotation.IntRange
import com.example.youtubeapi.data.remote.GoogleApiDataSource

class VideoRepository(
    private val googleApiDataSource: GoogleApiDataSource
) {
    suspend fun getMostPopularVideo(
        @IntRange(from = 72, to = 8192) maxHeight: Int = 512,
        @IntRange(from = 1, to = 50) maxResults: Int = 50,
        @IntRange(from = 72, to = 8192) maxWidth: Int = 512,
        videoCategoryId: String = "0"
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
}
