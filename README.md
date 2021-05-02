# BookSearch



### 요구사항 

- [카카오 도서 API](https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-book)를 이용한 도서 검색 앱

- 개발 언어는 Kotlin을 사용

- minSdk 23, targetSdk 30

- 스크롤 시 paging 기능을 제공해 연속적으로 검색하는 기능 제공

- 하나의 Activity와 2 개의 Fragment로 구성

  - 검색 리스트 결과 화면

  - 검색 결과에 대한 상세 화면

- 상단에 도서명을 입력할 수 있는 입력창을 제공해 도서명이 변경되면 해당 도서명으로 검색결과 노출

  - 검색 결과는 실시간으로 리스트 반영 필요

  - 좋아요 여부는 초기화 상태 false 여야 함

- 상세 화면에서 Like/UnLike 여부에 따라 이전 화면으로 돌아갈 경우 리스트에 반영 되어야 합니다.

  - 좋아요 여부는 새로운 키워드 검색 시 초기화 됩니다.

  - 리스트 화면에서는 좋아요 클릭을 별도 처리하지 않습니다.

- 메인리스트의 특정도서 클릭 시 상세 화면 fragment로 이동



### 참고자료 

###### ViewModel 에서 Event 전달, View 에서 Event 처리 

https://seunghyun.in/android/6/

###### Jetpack Navigation 

https://developer.android.com/guide/navigation/navigation-navigate

https://mparchive.tistory.com/171

###### LiveData

https://developer.android.com/topic/libraries/architecture/livedata?hl=ko

https://stackoverflow.com/questions/51299641/difference-of-setvalue-postvalue-in-mutablelivedata

