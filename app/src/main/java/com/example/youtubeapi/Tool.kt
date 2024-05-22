package com.example.youtubeapi

import androidx.core.text.HtmlCompat
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

fun String.escapeTag(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}

fun isoDateToKor(isoDate: String): String {
    val regex = Regex("(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})Z")
    val matchResult = regex.find(isoDate)

    if (matchResult != null) {
        val (year, month, day, hour, minute) = matchResult.destructured

        val hourInt = hour.toInt()
        val period = if (hourInt >= 12) "PM" else "AM"
        val hour12 = if (hourInt > 12) hourInt - 12 else if (hourInt == 0) 12 else hourInt

        val formattedDate = "게시일: ${year}년 ${month}월 ${day}일 ${hour12}:${minute}${period}"
        return formattedDate
    } else
    { return "" }
}

