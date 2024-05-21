package com.example.youtubeapi.data.model.dto

data class ChannelInfo(
    val id: String,
    val snippet: ChannelSnippet,
    val statistics: ChannelStatistics
)

//ChannelInfo.snippet.thumbnails.default
//ChannelInfo.statistics.subscriberCount

/* [getByChannelId에 대한 주석] - 내가 헷갈려서 기록함. GoogleApiDataSource.kt 에도 같은 내용 주석 있음.
https://developers.google.com/youtube/v3/docs/channels#properties 에서 각 항목 타입 및 용례를 알아볼 수 있다.
getByChannelId의 목적은 1. channelId를 input 2. output으로 썸네일 url, 구독자 수 가져오기 뿐이다.
2-1. 썸네일 가져오기
* snippet: object
* snippet.thumbnails: object
* snippet.thumbnails.default: object
* ㄴ default, medium, high 중 default(88px * 88px)를 사용하면 된다. 크기가 클 필요가 없기 때문이다.
* snippet.thumbnails.default.url: string. <
2-2. 구독자 수 가져오기
* statistics: object
* statistics.subscriberCount: unsigned long. <
subscriberCount의 100자리 이상은 반올림되어 있다. 우선은 toString()으로 출력 후,
다같이 있을 때 "구독자 수" + "000만 명, 0.0천 명" "0.0M Subscribers" 이런 식으로 표현하도록 regex 쓰든지 하자.
(그런 거 있다는 것만 제목 특수문자 html 처리할 때 보고 어떻게 쓴는지 문법 모름. 배우면 되겠지만... 우선 같이 하기
*/
