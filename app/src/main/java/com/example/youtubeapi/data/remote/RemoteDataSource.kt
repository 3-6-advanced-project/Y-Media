package com.example.youtubeapi.data.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.GOOGLE_API_URL_BASE
import com.example.youtubeapi.GOOGLE_API_URL_DEVELOP
import com.example.youtubeapi.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("${GOOGLE_API_URL_DEVELOP}youtube/v3/videos")
    suspend fun getMostPopularVideo(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxHeight") maxHeight: Int,
        @Query("maxResults") maxResults: Int,
        @Query("maxWidth") maxWidth: Int,
        @Query("regionCode") regionCode: String = "KR",
    ): VideoResponse
}