package com.example.youtubeapi.data.remote

import com.example.youtubeapi.API_KEY
import com.example.youtubeapi.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {
    @GET("youtube/v3/videos")
    suspend fun getMostPopularVideo(
        @Query("key") key: String = API_KEY,
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxHeight") maxHeight: Int,
        @Query("maxResults") maxResults: Int,
        @Query("maxWidth") maxWidth: Int,
        @Query("regionCode") regionCode: String = "KR",
    ): VideoResponse
}