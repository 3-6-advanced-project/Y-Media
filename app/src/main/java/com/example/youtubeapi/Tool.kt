package com.example.youtubeapi

import android.util.Log
import com.example.youtubeapi.data.model.dto.HomeVideo
import com.example.youtubeapi.data.model.dto.SearchVideo
import com.example.youtubeapi.data.model.dto.VideoById
import com.example.youtubeapi.presentation.uistate.VideoState


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

fun String.escapeTag(): String {
    val mapper = listOf(
        Pair("&nbsp;", " "),
        Pair("&lt;", "<"),
        Pair("&gt;", ">"),
        Pair("&amp;", "&"),
        Pair("&quot;", "\""),
        Pair("&#035;", "#"),
        Pair("&#039;", "'"),

        )
    val title = this
    mapper.forEach {
        title.replace(it.first, it.second)
        Log.d("replace", title)
    }

    return title
}