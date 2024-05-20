package com.example.youtubeapi

import com.example.youtubeapi.data.model.dto.Thumbnail
import com.example.youtubeapi.data.model.entity.VideoEntity

const val HomeFragment_TAG = "Home"
const val SearchFragment_TAG = "Search"
const val MyVideoFragment_TAG = "MyVideo"

const val GOOGLE_API_URL_BASE = BuildConfig.GOOGLE_API_URL_BASE
const val GOOGLE_API_URL_DEVELOP = BuildConfig.GOOGLE_API_URL_DEVELOP

val dummyData = mutableListOf(
    VideoEntity(
        rowId = 1L,
        kind = "youtube#channel",
        etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
        videoId = "vRheHVDYpcY",
        title = "DANIELLE - 저곳으로 (인어공주) (인어공주 OST)",
        description = "",
        channelTitle = "DisneyMusicKoreaVEVO",
        channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
        publishedAt = "2024-03-28T02:25:27Z",
        publishTime = "2024-03-28T02:25:27Z",
        duration = "03:37",
        thumbnailUrl = "https://i1.ytimg.com/vi/ho0d4rf1LwY/sddefault.jpg"
    ),
    VideoEntity(
        rowId = 1L,
        kind = "youtube#channel",
        etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
        videoId = "vRheHVDYpcY",
        title = "[About Jeans] :D Days (1일차) 호주에 돌리니 | 다니엘 브이로그",
        description = "",
        channelTitle = "NewJeans",
        channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
        publishedAt = "2024-03-28T02:25:27Z",
        publishTime = "2024-03-28T02:25:27Z",
        duration = "27:16",
        thumbnailUrl = "https://i1.ytimg.com/vi/oMhrUjdkZRs/sddefault.jpg"
    ),
    VideoEntity(
        rowId = 1L,
        kind = "youtube#channel",
        etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
        videoId = "vRheHVDYpcY",
        title = "MY 10 FAVORITES with NewJeans Danielle (Eng sub)",
        description = "",
        channelTitle = "Marie Claire Korea",
        channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
        publishedAt = "2024-03-28T02:25:27Z",
        publishTime = "2024-03-28T02:25:27Z",
        duration = "08:07",
        thumbnailUrl = "https://i1.ytimg.com/vi/buDBzX5vyBM/sddefault.jpg"
    ),
    VideoEntity(
        rowId = 1L,
        kind = "youtube#channel",
        etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
        videoId = "vRheHVDYpcY",
        title = "보자마자 마음이 힐링되는 하찮고 귀여운 동물들♥",
        description = "",
        channelTitle = "유머스낵",
        channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
        publishedAt = "2024-03-28T02:25:27Z",
        publishTime = "2024-03-28T02:25:27Z",
        duration = "03:06",
        thumbnailUrl = "https://i1.ytimg.com/vi/cSyzqumO0OI/sddefault.jpg"
    ),
    VideoEntity(
        rowId = 1L,
        kind = "youtube#channel",
        etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
        videoId = "vRheHVDYpcY",
        title = "[리무진 서비스 클립] Only Hope | 뉴진스 다니엘 | NewJeans DANIELLE",
        description = "",
        channelTitle = "KBS Kpop",
        channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
        publishedAt = "2024-03-28T02:25:27Z",
        publishTime = "2024-03-28T02:25:27Z",
        duration = "04:20",
        thumbnailUrl = "https://i1.ytimg.com/vi/Dmd1yenSN4o/sddefault.jpg"
    ),
    VideoEntity(
        rowId = 1L,
        kind = "youtube#channel",
        etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
        videoId = "vRheHVDYpcY",
        title = "[SUB] ※심쿵 주의※ 관리자가 보고 싶어서 만든 리트리버 인절미 모음집♥",
        description = "",
        channelTitle = "SBS Story",
        channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
        publishedAt = "2024-03-28T02:25:27Z",
        publishTime = "2024-03-28T02:25:27Z",
        duration = "12:52",
        thumbnailUrl = "https://i1.ytimg.com/vi/NfF9BFwwfnw/sddefault.jpg"
    ),
    VideoEntity(
        rowId = 1L,
        kind = "youtube#channel",
        etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
        videoId = "vRheHVDYpcY",
        title = "(New Jeans) 뉴진스 다니엘 입덕모음.zip",
        description = "",
        channelTitle = "채원닝 케이팝",
        channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
        publishedAt = "2024-03-28T02:25:27Z",
        publishTime = "2024-03-28T02:25:27Z",
        duration = "03:37",
        thumbnailUrl = "https://i1.ytimg.com/vi/TmIMRzG93d4/sddefault.jpg"
    ),
    VideoEntity(
        rowId = 1L,
        kind = "youtube#channel",
        etag = "HyrHa3Q1O2Q7MJ9dfdCXINrw5yY",
        videoId = "vRheHVDYpcY",
        title = "밤톨이를 하나 주웠는데 강아지가 됐어요",
        description = "",
        channelTitle = "SBS TV동물농장x애니멀봐 공식 유튜브 채널입니다!",
        channelId = "UCCjHwLqUSjxB7NtvUK9fcmw",
        publishedAt = "2024-03-28T02:25:27Z",
        publishTime = "2024-03-28T02:25:27Z",
        duration = "05:07",
        thumbnailUrl = "https://i1.ytimg.com/vi/rdpAs4ER7PA/sddefault.jpg"
    ),
)
