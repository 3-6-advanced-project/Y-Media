package com.example.youtubeapi.data.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.model.dto.ChannelsResponse
import com.example.youtubeapi.data.model.dto.VideoResponse
import com.example.youtubeapi.data.model.dto.HomeVideo
import com.example.youtubeapi.data.model.dto.SearchVideo
import com.example.youtubeapi.data.model.dto.VideoCategoryResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApiDataSource {

    @GET("youtube/v3/videos")
    suspend fun getMostPopularVideo(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("part") part: String = "snippet,contentDetails",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxHeight") maxHeight: Int,
        @Query("maxResults") maxResults: Int,
        @Query("maxWidth") maxWidth: Int,
        @Query("regionCode") regionCode: String = "KR",
        @Query("videoCategoryId") videoCategoryId: String,
    ): VideoResponse<HomeVideo>

    /** 동영상 검색 API
     *
     * API 문서: https://developers.google.com/youtube/v3/docs/search/list?hl=ko#type
     * 실사용: https://www.googleapis.com/youtube/v3/search?
     *
     * @param query 검색어
     * @param maxResults 반환 데이터 개수 (0 ~ 50, default 5)
     * @param relevanceLanguage 지정된 언어와 가장 관련성이 높은 검색결과를 반환: ISO 639-1 두 자리 언어 코드
     *
     * @param videoDuration 필터 (영상 시간)
     *    any – 동영상 길이를 기준으로 동영상 검색결과를 필터링하지 않습니다. 기본값입니다.
     *    long – 20분보다 긴 동영상만 포함합니다.
     *    medium – 4분 이상 20분 이하인 동영상만 포함합니다.
     *    short – 4분 미만인 동영상만 포함합니다.
     */
    @GET("youtube/v3/search")
    suspend fun getSearchVideo(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("videoDuration") videoDuration: String,
        @Query("videoDefinition") videoDefinition: String,
        @Query("type") type: String = "video", // video, channel, playlist
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "KR",
        @Query("relevanceLanguage") relevanceLanguage: String = "ko",
    ): VideoResponse<SearchVideo>

    @GET("youtube/v3/videoCategories")
    suspend fun getVideoCategories(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "KR"
    ): VideoCategoryResponse

    @GET("youtube/v3/channels")
    suspend fun getChannels(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("part") part: String = "snippet",
        @Query("id") id: String
    ): ChannelsResponse
}