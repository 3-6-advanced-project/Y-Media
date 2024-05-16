package com.example.youtubeapi

import com.example.youtubeapi.data.model.dto.Thumbnail
import com.example.youtubeapi.data.model.entity.VideoEntity

const val HomeFragment_TAG = "Home"
const val SearchFragment_TAG = "Search"
const val MyVideoFragment_TAG = "MyVideo"

const val GOOGLE_API_URL_BASE = BuildConfig.GOOGLE_API_URL_BASE
const val GOOGLE_API_URL_DEVELOP = BuildConfig.GOOGLE_API_URL_DEVELOP


val bookmarkDummy = VideoEntity(
    rowId = 1L,
    kind = "youtube#channel",
    etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
    videoId = "vRheHVDYpcY",
    title = "925notfound - Topic",
    description = "",
    channelTitle = "925notfound - Topic",
    channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
    publishedAt = "2024-03-28T02:25:27Z",
    publishTime = "2024-03-28T02:25:27Z",
    thumbnail = Thumbnail(
        url = "https://yt3.ggpht.com/W0oCPGjS20ws_0k1oMFmMIx3NgMy9PTH" +
                "DWzUbxPdBH_GtN8mQLMRtDGpZwlwBK6iwqSaMFmOpxQ=s88-c-k-c0xffffffff-no-rj-mo"
    )
)


/**
 * "kind": "youtube#searchResult",
 *             "etag": "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
 *             "id": {
 *                 "kind": "youtube#channel",
 *                 "channelId": "UCCjHwLqUSjxB7NtvUK9fcmw"
 *             },
 *             "snippet": {
 *                 "publishedAt": "2024-03-28T02:25:27Z",
 *                 "channelId": "UCCjHwLqUSjxB7NtvUK9fcmw",
 *                 "title": "925notfound - Topic",
 *                 "description": "",
 *                 "thumbnails": {
 *                     "default": {
 *                         "url": "https://yt3.ggpht.com/W0oCPGjS20ws_0k1oMFmMIx3NgMy9PTHDWzUbxPdBH_GtN8mQLMRtDGpZwlwBK6iwqSaMFmOpxQ=s88-c-k-c0xffffffff-no-rj-mo"
 *                     },
 *                     "medium": {
 *                         "url": "https://yt3.ggpht.com/W0oCPGjS20ws_0k1oMFmMIx3NgMy9PTHDWzUbxPdBH_GtN8mQLMRtDGpZwlwBK6iwqSaMFmOpxQ=s240-c-k-c0xffffffff-no-rj-mo"
 *                     },
 *                     "high": {
 *                         "url": "https://yt3.ggpht.com/W0oCPGjS20ws_0k1oMFmMIx3NgMy9PTHDWzUbxPdBH_GtN8mQLMRtDGpZwlwBK6iwqSaMFmOpxQ=s800-c-k-c0xffffffff-no-rj-mo"
 *                     }
 *                 },
 *                 "channelTitle": "925notfound - Topic",
 *                 "liveBroadcastContent": "none",
 *                 "publishTime": "2024-03-28T02:25:27Z"
 *             }
 * */