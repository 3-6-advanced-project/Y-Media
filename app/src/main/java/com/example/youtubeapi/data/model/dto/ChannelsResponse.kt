package com.example.youtubeapi.data.model.dto

data class ChannelsResponse(
    val kind: String,
    val etag: String,
    val pageInfo: PageInfo,
    val items: List<Channel>
)