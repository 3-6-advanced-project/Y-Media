package com.example.youtubeapi.data.model.dto

import java.util.DoubleSummaryStatistics

data class HomeVideo(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: VideoSnippet,
    val contentDetails: VideoContentDetails,
)

data class SearchVideo(
    val kind: String,
    val etag: String,
    val id: Id,
    val snippet: VideoSnippet,
)
data class VideoById(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: VideoSnippet, //에서 channelId, channelTitle, title, description, thumbnails
    val contentDetails: VideoContentDetails, //영상 시간
)

data class Id(
    val kind: String,
    val videoId: String,
)

data class VideoSnippet(
    val publishedAt: String, // "2022-03-13T02:12:08Z"
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val categoryId: String,
)

data class VideoContentDetails(
    val duration: String, // PT format: PT23M24S -> 23분 24초
    val dimension: String,
    val definition: String,
)

data class Thumbnails(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail,
)

data class Thumbnail(
    val url: String,
)

//channel.kt에서도 ChannelSnippet 사용하고 있어 충돌 위험 > channel.kt로 이동

//data class ChannelByVideoId(
//    val kind: String,
//    val etag: String,
//    val id: String,
//    val snippet: ChannelSnippet,
//    val contentDetails: VideoContentDetails,
//)
//
//data class ChannelSnippet(
//    val title: String,
//    val description: String,
//    val customUrl: String,
//    val thumbnails: Thumbnails, //
//    val statistics: ChannelStatistics
//)
//
//data class ChannelStatistics(
//    val subscriberCount: Long
//)
