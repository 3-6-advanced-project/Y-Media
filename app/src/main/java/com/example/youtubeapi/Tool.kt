package com.example.youtubeapi

import android.provider.MediaStore
import com.example.youtubeapi.data.model.dto.HomeVideo


/**
 * @param videos api call을 통해 받은 값.
 * @return Channel api 검색에 활용할 id들을 ,로 합친 스트링입니다.
 * */
fun extractChannelIdStringFromVideos(videos: List<HomeVideo>): String {
    val channelIdSet = mutableSetOf<String>()
    videos.forEach { homeVideo ->
        channelIdSet.add(homeVideo.snippet.channelId)
    }
    return channelIdSet.joinToString(",")
}