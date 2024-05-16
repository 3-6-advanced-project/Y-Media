package com.example.youtubeapi.data.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.GOOGLE_API_URL_BASE
import com.example.youtubeapi.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchDataSource {
    /** 동영상 검색 API
     *
     * API 문서: https://developers.google.com/youtube/v3/docs/search/list?hl=ko#type
     * 실사용: https://www.googleapis.com/youtube/v3/search?
     *
     * 검색 시, 필터나 정렬을 적용할 수 있는 쿼리 리스트업
     * videoDefinition - 필터(화질)
     *    any - 해상도에 관계없이 모든 동영상을 반환합니다.
     *    high – HD 동영상만 검색합니다.
     *    standard – 표준 화질 동영상만 검색합니다.
     *
     *    * 위 속성을 쓸 경우 type 값으로 "video"를 전달해야함
     *
     * videoDuration - 필터(영상 시간)
     *    any – 동영상 길이를 기준으로 동영상 검색결과를 필터링하지 않습니다. 기본값입니다.
     *    long – 20분보다 긴 동영상만 포함합니다.
     *    medium – 4분 이상 20분 이하인 동영상만 포함합니다.
     *    short – 4분 미만인 동영상만 포함합니다.
     *
     * order(기본값 relevance) - 정렬
     *    date – 리소스를 만든 날짜를 기준으로 최근 항목부터 시간 순서대로 리소스를 정렬합니다.
     *    rating – 높은 평점에서 낮은 평점순으로 리소스가 정렬됩니다.
     *    relevance – 검색어와의 관련성을 기준으로 리소스를 정렬합니다. 이 매개변수의 기본값입니다.
     *    title – 제목에 따라 알파벳순으로 리소스를 정렬합니다.
     *    videoCount – 업로드된 동영상 수에 따라 채널을 내림차순으로 정렬합니다.
     *    viewCount – 리소스가 조회수가 높은 순에서 낮은 순으로 정렬됩니다. 실시간 방송의 경우 시청자 수를 기준으로 동영상이 정렬됩니다.
     *
     *
     */
    @GET("youtube/v3/search")
    suspend fun getSearchVideo(
        @Query("key") key: String = BuildConfig.GOOGLE_API_KEY,
        @Query("q") query: String, // 검색어
        @Query("maxResults") maxResults: Int,
        @Query("videoDefinition") videoDefinition: String,
        @Query("type") type: String = "video", // video, channel, playlist
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "KR",
    ): VideoResponse
}