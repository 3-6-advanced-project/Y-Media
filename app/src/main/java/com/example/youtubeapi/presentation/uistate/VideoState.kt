package com.example.youtubeapi.presentation.uistate

import com.example.youtubeapi.data.model.dto.HomeVideo
import com.example.youtubeapi.data.model.dto.SearchVideo
import com.example.youtubeapi.data.model.dto.Thumbnail
import com.example.youtubeapi.data.model.dto.VideoById

data class VideoState(
    val id: String,
    val title: String,
    val description: String,
    val channelTitle: String,
    val thumbnail: Thumbnail,
    val duration: String = "",
    val categoryId: String = "",
    val publishedAt: String,
    val channelId: String = "" //디폴트값 지정 헉!
)

/**
 * thumbnail resolution default
 * */
fun HomeVideo.asVideoState() = VideoState(
    id = id,
    title = snippet.title,
    description = snippet.description,
    channelTitle = snippet.channelTitle,
    thumbnail = snippet.thumbnails.high,
    duration = contentDetails.duration,
    categoryId = snippet.categoryId,
    publishedAt = snippet.publishedAt
)

fun SearchVideo.asVideoState() = VideoState(
    id = id.videoId,
    title = snippet.title,
    description = snippet.description,
    channelTitle = snippet.channelTitle,
    thumbnail = snippet.thumbnails.high,
    publishedAt = snippet.publishedAt
)

fun VideoById.asVideoState() = VideoState(
    id = id,
    title = snippet.title,
    channelId = snippet.channelId,
    description = snippet.description,
    channelTitle = snippet.channelTitle,
    thumbnail = snippet.thumbnails.high, //고화질 썸네일
    publishedAt = snippet.publishedAt //안쓰지만 없으면 꼬이니까 우선 냅두자/
)
