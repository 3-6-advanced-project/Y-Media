package com.example.youtubeapi.data.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class VideoEntity(
    @PrimaryKey(autoGenerate = true) val rowId: Long,
    val kind: String,
    val etag: String,
    val videoId: String,
    val title: String,
    val description: String,
    val channelTitle: String,
    val channelId: String,
    val publishedAt: String,
    val publishTime: String,
    @Embedded val thumbnail: Thumbnail
)

data class Video(
    val kind: String,
    val etag: String,
    val id: Id,
    val snippet: Snippet,
)

data class Id(
    val kind: String,
    val videoId: String,
)

data class Snippet(
    val publishedAt: String, // "2022-03-13T02:12:08Z"
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val liveBroadcastContent: String,
    val publishTime: String,
)

data class Thumbnails(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail
)

data class Thumbnail(
    val url: String,
    val width: Int,
    val height: Int
)

/**
 * {
 *  *             "kind": "youtube#searchResult",
 *  *             "etag": "P_OMPYcO0DKaHidLLN0rjYCAMzc",
 *  *             "id": {
 *  *                 "kind": "youtube#video",
 *  *                 "videoId": "Sg95nokmww8"
 *  *             },
 *  *             "snippet": {
 *  *                 "publishedAt": "2022-03-13T02:12:08Z",
 *  *                 "channelId": "UCdyeqVNyJehPmKBEJ520rhg",
 *  *                 "title": "집중력과 인내심이 필요한 장난감 #shorts",
 *  *                 "description": "",
 *  *                 "thumbnails": {
 *  *                     "default": {
 *  *                         "url": "https://i.ytimg.com/vi/Sg95nokmww8/default.jpg",
 *  *                         "width": 120,
 *  *                         "height": 90
 *  *                     },
 *  *                     "medium": {
 *  *                         "url": "https://i.ytimg.com/vi/Sg95nokmww8/mqdefault.jpg",
 *  *                         "width": 320,
 *  *                         "height": 180
 *  *                     },
 *  *                     "high": {
 *  *                         "url": "https://i.ytimg.com/vi/Sg95nokmww8/hqdefault.jpg",
 *  *                         "width": 480,
 *  *                         "height": 360
 *  *                     }
 *  *                 },
 *  *                 "channelTitle": "신사장",
 *  *                 "liveBroadcastContent": "none",
 *  *                 "publishTime": "2022-03-13T02:12:08Z"
 *  *             }
 *  *         },
 * */