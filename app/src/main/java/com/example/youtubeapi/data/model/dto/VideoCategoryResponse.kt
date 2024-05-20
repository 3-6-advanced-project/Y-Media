package com.example.youtubeapi.data.model.dto

data class VideoCategoryResponse(
    val kind: String,
    val etag: String,
    val items: List<VideoCategory>
)
