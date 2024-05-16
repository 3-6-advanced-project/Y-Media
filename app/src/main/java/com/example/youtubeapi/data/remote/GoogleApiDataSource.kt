package com.example.youtubeapi.data.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.model.dto.VideoCategoryResponse
import com.example.youtubeapi.data.model.dto.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * BASE_URL = https://www.googleapis.com/
 *
 * */
interface GoogleApiDataSource {
    @GET("youtube/v3/videos")
    suspend fun getMostPopularVideo(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxHeight") maxHeight: Int,
        @Query("maxResults") maxResults: Int,
        @Query("maxWidth") maxWidth: Int,
        @Query("regionCode") regionCode: String = "KR",
        @Query("videoCategoryId") videoCategoryId: String,
    ): VideoResponse

    @GET("youtube/v3/videoCategories")
    suspend fun getVideoCategories(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "KR"
    ): VideoCategoryResponse
}
