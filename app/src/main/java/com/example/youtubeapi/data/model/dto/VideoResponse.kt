package com.example.youtubeapi.data.model.dto

data class VideoResponse(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: PageInfo,
    val items: List<Video>
)

data class PageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)





/**
 *
 *
 * {
 *     "kind": "youtube#searchListResponse",
 *     "etag": "3XWX1fWrcCup3r4V--6a2YAVFHU",
 *     "nextPageToken": "CAUQAA",
 *     "regionCode": "KR",
 *     "pageInfo": {
 *         "totalResults": 1000000,
 *         "resultsPerPage": 5
 *     },
 *     "items": [
 *         {
 *             "kind": "youtube#searchResult",
 *             "etag": "P_OMPYcO0DKaHidLLN0rjYCAMzc",
 *             "id": {
 *                 "kind": "youtube#video",
 *                 "videoId": "Sg95nokmww8"
 *             },
 *             "snippet": {
 *                 "publishedAt": "2022-03-13T02:12:08Z",
 *                 "channelId": "UCdyeqVNyJehPmKBEJ520rhg",
 *                 "title": "집중력과 인내심이 필요한 장난감 #shorts",
 *                 "description": "",
 *                 "thumbnails": {
 *                     "default": {
 *                         "url": "https://i.ytimg.com/vi/Sg95nokmww8/default.jpg",
 *                         "width": 120,
 *                         "height": 90
 *                     },
 *                     "medium": {
 *                         "url": "https://i.ytimg.com/vi/Sg95nokmww8/mqdefault.jpg",
 *                         "width": 320,
 *                         "height": 180
 *                     },
 *                     "high": {
 *                         "url": "https://i.ytimg.com/vi/Sg95nokmww8/hqdefault.jpg",
 *                         "width": 480,
 *                         "height": 360
 *                     }
 *                 },
 *                 "channelTitle": "신사장",
 *                 "liveBroadcastContent": "none",
 *                 "publishTime": "2022-03-13T02:12:08Z"
 *             }
 *         },
 *         {
 *             "kind": "youtube#searchResult",
 *             "etag": "OTTNS1vKYaB32NAthEQDvrxx3hM",
 *             "id": {
 *                 "kind": "youtube#video",
 *                 "videoId": "WnAkXTcFuzA"
 *             },
 *             "snippet": {
 *                 "publishedAt": "2022-12-18T11:00:28Z",
 *                 "channelId": "UCtctOXlHYeQyuXSgW861nMA",
 *                 "title": "믿을 수 있는 사람이랑만 하세요 ⭕️ Trust Challenge",
 *                 "description": "온라인으로 텀블링 배우기 https://101.gg/team1llusion-class 처음부터 잘 하는 사람은 없습니다. 유연성이 안 좋아서, 근력이 약해서, ...",
 *                 "thumbnails": {
 *                     "default": {
 *                         "url": "https://i.ytimg.com/vi/WnAkXTcFuzA/default.jpg",
 *                         "width": 120,
 *                         "height": 90
 *                     },
 *                     "medium": {
 *                         "url": "https://i.ytimg.com/vi/WnAkXTcFuzA/mqdefault.jpg",
 *                         "width": 320,
 *                         "height": 180
 *                     },
 *                     "high": {
 *                         "url": "https://i.ytimg.com/vi/WnAkXTcFuzA/hqdefault.jpg",
 *                         "width": 480,
 *                         "height": 360
 *                     }
 *                 },
 *                 "channelTitle": "팀일루션 노성율 - TEAM1LLUSION",
 *                 "liveBroadcastContent": "none",
 *                 "publishTime": "2022-12-18T11:00:28Z"
 *             }
 *         },
 *         {
 *             "kind": "youtube#searchResult",
 *             "etag": "keYSKyxWaAJYZ5hHWvdfF_NMEVw",
 *             "id": {
 *                 "kind": "youtube#video",
 *                 "videoId": "8Yn6LtF60Us"
 *             },
 *             "snippet": {
 *                 "publishedAt": "2022-03-02T19:54:38Z",
 *                 "channelId": "UCRKuzYFOx5XE56jJi9QE43Q",
 *                 "title": "Axolotl Portal 🦎",
 *                 "description": "shorts Portal Feeding Rings + duckweed: https://fishportals.com Instagram: https://www.instagram.com/fish4everchannel/ TikTok: ...",
 *                 "thumbnails": {
 *                     "default": {
 *                         "url": "https://i.ytimg.com/vi/8Yn6LtF60Us/default.jpg",
 *                         "width": 120,
 *                         "height": 90
 *                     },
 *                     "medium": {
 *                         "url": "https://i.ytimg.com/vi/8Yn6LtF60Us/mqdefault.jpg",
 *                         "width": 320,
 *                         "height": 180
 *                     },
 *                     "high": {
 *                         "url": "https://i.ytimg.com/vi/8Yn6LtF60Us/hqdefault.jpg",
 *                         "width": 480,
 *                         "height": 360
 *                     }
 *                 },
 *                 "channelTitle": "Fish4Ever",
 *                 "liveBroadcastContent": "none",
 *                 "publishTime": "2022-03-02T19:54:38Z"
 *             }
 *         },
 *         {
 *             "kind": "youtube#searchResult",
 *             "etag": "HPq5Ss16Q2PdINbPUw5pdLngQnI",
 *             "id": {
 *                 "kind": "youtube#video",
 *                 "videoId": "Ipb1clXh6MM"
 *             },
 *             "snippet": {
 *                 "publishedAt": "2021-02-04T20:19:42Z",
 *                 "channelId": "UC4GaWcWGhh5n1WmJvb1oBkA",
 *                 "title": "How to Make a Working Lego Water Dispenser!! #shorts",
 *                 "description": "shorts This is a working Lego water dispenser! If you enjoyed, please leave a like, and subscribe to not miss any content!",
 *                 "thumbnails": {
 *                     "default": {
 *                         "url": "https://i.ytimg.com/vi/Ipb1clXh6MM/default.jpg",
 *                         "width": 120,
 *                         "height": 90
 *                     },
 *                     "medium": {
 *                         "url": "https://i.ytimg.com/vi/Ipb1clXh6MM/mqdefault.jpg",
 *                         "width": 320,
 *                         "height": 180
 *                     },
 *                     "high": {
 *                         "url": "https://i.ytimg.com/vi/Ipb1clXh6MM/hqdefault.jpg",
 *                         "width": 480,
 *                         "height": 360
 *                     }
 *                 },
 *                 "channelTitle": "PuzzLEGO",
 *                 "liveBroadcastContent": "none",
 *                 "publishTime": "2021-02-04T20:19:42Z"
 *             }
 *         },
 *         {
 *             "kind": "youtube#searchResult",
 *             "etag": "fsS7SriyIcDxK6o4yRz4diYOL8o",
 *             "id": {
 *                 "kind": "youtube#video",
 *                 "videoId": "Zh0tdlD7CtU"
 *             },
 *             "snippet": {
 *                 "publishedAt": "2023-12-18T00:27:42Z",
 *                 "channelId": "UCXo4tjyjVydwOaAq59ya1Ag",
 *                 "title": "24/7 Psychological stability music for anxious dogs｜Separation Anxiety Music &amp; Stress Relief Music",
 *                 "description": "Psychological stability music for anxious dogs｜Separation Anxiety Music & Stress Relief Music Nice to meet you! Thank you for ...",
 *                 "thumbnails": {
 *                     "default": {
 *                         "url": "https://i.ytimg.com/vi/Zh0tdlD7CtU/default_live.jpg",
 *                         "width": 120,
 *                         "height": 90
 *                     },
 *                     "medium": {
 *                         "url": "https://i.ytimg.com/vi/Zh0tdlD7CtU/mqdefault_live.jpg",
 *                         "width": 320,
 *                         "height": 180
 *                     },
 *                     "high": {
 *                         "url": "https://i.ytimg.com/vi/Zh0tdlD7CtU/hqdefault_live.jpg",
 *                         "width": 480,
 *                         "height": 360
 *                     }
 *                 },
 *                 "channelTitle": "Sweet Pet Music",
 *                 "liveBroadcastContent": "live",
 *                 "publishTime": "2023-12-18T00:27:42Z"
 *             }
 *         }
 *     ]
 * }
 *
 * */