package com.example.youtubeapi.presentation.uistate

import com.example.youtubeapi.data.model.dto.Thumbnail
import com.example.youtubeapi.data.model.dto.Video

data class VideoState(
    val id: String,
    val title: String,
    val description: String,
    val channelTitle: String,
    val thumbnail: Thumbnail,
    val duration: String,
    val categoryId: String,
    val publishedAt: String,
)

/**
 * thumbnail resolution default
 * */
fun Video.asVideoState() = VideoState(
    id = id.videoId,
    title = snippet.title,
    description = snippet.description,
    channelTitle = snippet.channelTitle,
    thumbnail = snippet.thumbnails.default,
    duration = contentDetails.duration,
    categoryId = snippet.categoryId,
    publishedAt = snippet.publishedAt
)
