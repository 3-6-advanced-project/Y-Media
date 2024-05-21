package com.example.youtubeapi.data.model.dto

data class Channel(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: ChannelSnippet
)

data class ChannelSnippet(
    val title: String,
    val description: String,
    val customUrl: String,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val country: String,
    val statistics: ChannelStatistics //추가 요소
) //TODO: HomeFragment에서도 사용중인 ChannelSnippet은 statistics null 처리 필요. DetailFragment에서만 사용 예정

data class ChannelStatistics(
    val subscriberCount: Long
)
