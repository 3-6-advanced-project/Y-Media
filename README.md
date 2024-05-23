# Project Documentation

## 개요
이 문서는 안드로이드 애플리케이션에서 구현된 기능들을 소개합니다.
우리 앱은 Android Studio를 활용하여 사용자가 비디오를 원활하게 탐색하고,
자세한 비디오 정보를 제공 받도록 설계되었습니다.

### 시연 영상
애플리케이션의 시연은 [여기](https://www.youtube.com/watch?v=dXAK1s-xX44&t=1s)에서 확인할 수 있습니다.

### 개발 일정 (2024/05/13 ~ 2024/05/23)
| 날짜             | 수행한 Task                     |
|----------------|------------------------------|
| 05/13(월)       | SA 작성, 디자인 레퍼런스 탐색 |
| 05/14(화)       | 디자인 수정, 팀 규칙 및 협업 규칙 논의      |
| 05/16(목)~18(토) | 집중 개발 기간, PR 및 코드리뷰          |
| 05/20(월)       | 개발 마무리 및 최종 PR, 데모 시나리오 구상   |
| 05/21(화)       | 데모 시나리오 테스트 및 이슈 해결, 리팩토링    |
| 05/22(수)       | 리팩토링, 시연 영상 녹화, PPT 트러블슈팅 작성 |
| 05/23(목)       | PPT 구조 논의, 발표 연습 및 피드백       |

### 역할 분담
| 이름  | 담당 |
|-----|-|
| 이준영 | 프로젝트 생성, MainActivity, HomeFragment, Room |
| 김태영 | SearchFragment, SharedPreferences |
| 김민경 | MyVideoFragment |
| 황수영 | VideoDetailFragment |
| 공통 | 데이터 구조, Retrofit + Youtube v3 API, ViewModel 등 |

### 와이어 프레임
![와이어프레임](https://github.com/rlaxodud214/NBC-SearchResources/assets/52482206/9103039e-e5a4-437e-8b54-31c19c8f6b9d)

## 사용한 기술들

### 핵심 기술
- **GitHub Organization**: 코드 관리와 버전 관리를 용이하게 합니다.
- **Fragment**: 모듈형 앱 개발에 사용됩니다.
- **ViewPager + TabLayout**: 효율적인 Fragment UI 관리, 적절한 프래그먼트 관리 위젯을 사용하여 세 주요 화면을 구현하고 사용자 경험을 최적화합니다.
- **YouTube API v3 + OkHttp + Retrofit**: YouTube로부터 비디오 데이터를 가져오는데 사용됩니다.
- **Bundle**: 프래그먼트 간 VideoId 전달에 사용됩니다.
- **Room**: 좋아요 한 비디오의 로컬 데이터베이스 저장 관리.

### 추가 기술
- **MVVM, ViewModel**: UI 관련 데이터를 생명주기를 고려하여 관리하는 아키텍처 패턴.
- **Coroutines**: 비동기 작업을 처리하여 UI 반응성을 향상시킵니다.
- **Coil**: Kotlin에 맞춤화된 이미지 로딩 라이브러리.
- **SharedPreferences**: 마지막 검색 쿼리를 저장하는 데 사용됩니다.

## 기능 구현 목록

---

### 홈 화면 (HomeFragment)
- YouTube 영상중 인기 콘텐츠를 표시합니다.
- 스크롤 가능한 `RecyclerView`를 구현하여 다음 내용을 보여줍니다:
    - **가장 인기 있는 비디오 (Most Popular Videos)**: `videos` 엔드포인트와 `chart=mostPopular` 파라미터를 사용하여 데이터를 가져옵니다.
    - **카테고리 비디오 (Category Videos)**:
        1. `videoCategories` 엔드포인트를 사용하여 비디오 카테고리를 조회합니다.
        2. 선택된 카테고리의 인기 비디오를 `videos` 엔드포인트와 `videoCategoryId=선택된 카테고리` 파라미터를 사용하여 조회합니다.
    - **카테고리 채널 (Category Channels)**:
        1. `videos` 엔드포인트와 `chart=mostPopular, videoCategoryId=선택된 카테고리` 파라미터를 사용하여 channelId 값들을 수집한 뒤, <br>
           `channels` 엔드포인트와 `id=List<channelID>`를 사용하여 채널의 상세 정보를 가져오고 주요 채널 정보들을 표시합니다.


### 비디오 검색 (SearchFragment)
- 검색바와 검색 결과를 표시할 `RecyclerView`를 포함합니다.
- YouTube Data API v3의 `search` 엔드포인트와 `q=검색어, videoDuration=선택된 필터` 파라미터를 사용하여 필터링된 비디오 데이터를 가져옵니다.
- 네트워크 문제, API 제한, 검색 결과 없음 등의 오류를 처리합니다.
- SharedPreferences를 이용하여 마지막으로 검색한 검색어를 저장하고 불러옵니다.


### 마이 페이지 (MyVideoFragment)
- 사용자의 프로필 정보 및 '좋아요'한 비디오 목록을 표시합니다.
- `RecyclerView`를 사용하여 '좋아요'한 비디오 목록을 나열합니다.
- 로컬 저장소 Room DB에서 '좋아요'한 비디오를 가져와 표시합니다.

---

### 비디오 상세 정보 (VideoDetailFragment)
- 위 3개의 Fragment에서 비디오 클릭 시, VideoDatailFragment로 이동되어 상세 정보 UI를 제공합니다. (이때 Bundle로 VideoId값을 전달 받는다)
- YouTube Data API v3의 `videos` 엔드포인트와 `videoId` 파라미터를 사용하여 선택된 비디오 데이터를 가져옵니다.
- "좋아요" 버튼을 구현하여 비디오를 로컬 데이터베이스 Room DB에 저장하거나, 삭제할 수 있습니다.