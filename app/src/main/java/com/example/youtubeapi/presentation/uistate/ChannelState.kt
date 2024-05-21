package com.example.youtubeapi.presentation.uistate

import com.example.youtubeapi.data.model.dto.ChannelInfo
import com.example.youtubeapi.data.model.dto.Thumbnail

data class ChannelState(
    val id: String,
    val thumbnail: Thumbnail,
    val subscribers: Long
)

fun ChannelInfo.asChannelState() = ChannelState(
    id = id,
    thumbnail =  snippet.thumbnails.default,
    subscribers = statistics.subscriberCount
)