package com.example.youtubeapi.data.model.dto

data class VideoCategory(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: VideoCategorySnippet
)

data class VideoCategorySnippet(
    val channelId: String,
    val title: String,
    val assignable: Boolean
)