package com.example.youtubeapi.data.model.dto

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
    val categoryId: String
)

data class VideoContentDetails(
    val duration: String, // PT format: PT23M24S -> 23분 24초
    val dimension: String,
    val definition: String,
)

data class Thumbnails(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail
)

data class Thumbnail(
    val url: String,
)
