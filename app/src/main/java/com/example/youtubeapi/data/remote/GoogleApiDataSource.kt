package com.example.youtubeapi.data.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.model.dto.ChannelsResponse
import com.example.youtubeapi.data.model.dto.VideoResponse
import com.example.youtubeapi.data.model.dto.HomeVideo
import com.example.youtubeapi.data.model.dto.SearchVideo
import com.example.youtubeapi.data.model.dto.VideoCategoryResponse
import com.example.youtubeapi.data.model.dto.VideoById
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

    @GET("youtube/v3/videos")
    suspend fun getVideoById(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("id") id: String,
        @Query("part") part: String = "snippet"
    ): VideoResponse<VideoById>

    //https://developers.google.com/youtube/v3/docs/channels/list
    /*
    * 사용할 api: v3/channels. ㅇㅋ
    * suspend fun getChannelById에서 videoId: String을 받는다.
    * VideoResponse에 사용할 타입 찾는 중.
    *
    * */
    @GET("youtube/v3/channels")
    suspend fun getByChannelId(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("id") channelId: String, //channel ID
        @Query("part") part: String = "snippet, statistics"
    )
}
/* [getByChannelId에 대한 주석] - 내가 헷갈려서 기록함
https://developers.google.com/youtube/v3/docs/channels#properties 에서 각 항목 타입 및 용례를 알아볼 수 있다.
getByChannelId의 목적은 1. channelId를 input 2. output으로 썸네일 url, 구독자 수 가져오기 뿐이다.
2-1. 썸네일 가져오기
* snippet: object
* snippet.thumbnails: object
* snippet.thumbnails.default: object
* ㄴ default, medium, high 중 default(88px * 88px)를 사용하면 된다. 크기가 클 필요가 없기 때문이다.
* snippet.thumbnails.default.url: string. <

2-2. 구독자 수 가져오기
* statistics: object
* statistics.subscriberCount: unsigned long. <
subscriberCount의 100자리 이상은 반올림되어 있다. 우선은 toString()으로 출력 후,
다같이 있을 때 "구독자 수" + "000만 명, 0.0천 명" "0.0M Subscribers" 이런 식으로 표현하도록 regex 쓰든지 하자.
(그런 거 있다는 것만 제목 특수문자 html 처리할 때 보고 어떻게 쓴는지 문법 모름. 배우면 되겠지만... 우선 같이 하기
*/
