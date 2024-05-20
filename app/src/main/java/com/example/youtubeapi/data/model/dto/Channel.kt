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
    val country: String
)
