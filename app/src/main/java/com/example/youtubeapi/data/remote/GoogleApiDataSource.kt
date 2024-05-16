package com.example.youtubeapi.data.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.model.dto.HomeVideo
import com.example.youtubeapi.data.model.dto.SearchVideo
import com.example.youtubeapi.data.model.dto.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

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
    ): VideoResponse<HomeVideo>

    @GET("youtube/v3/search")
    suspend fun getSearchVideo(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("q") query: String, // 검색어
        @Query("maxResults") maxResults: Int,
        @Query("videoDefinition") videoDefinition: String,
        @Query("type") type: String = "video", // video, channel, playlist
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "KR",
        // @Query("relevanceLanguage") relevanceLanguage: String = "KR", // 지정된 언어와 가장 관련성이 높은 검색결과를 반환

    ): VideoResponse<SearchVideo>
}