### 용어 정의
- `NovelBoard` : 소설 릴레이를 위한 기본 대시보드. 
- `Opening` : NovelBoard 의 첫머리. 
- `Article` : Opening 에 이어 릴레이 된 글. Relay 또는 Fork 를 통해 생성된다. 
  - `Relay` : 제일 마지막 Article 에 이어짓기
  - `Fork` : 생성된 Article 외 갈래짓기


### 기술 정의
- Opening 은 NovelBoard 생성시 함께 생성된다.
- NovelBoard 에  Article 을 ( relay ) 할 수 있다.
- NovelBoard 에 Article 을 ( fork ) 할 수 있다.
- relay 된 Article 은 부모(ArticleKey)를 알고 있다.
- fork 된 Article 은 같은 부모(ArticleKey)을 공유하는 형제 Article 이 있다.
- 부모키(ArticleKey) 뭘로 이루어질 것인가. view 에 뿌려질 순서를 id 로 알 수 있을까. 📌 중요한듯..


### 정책 정의
- maxStageSize 가 찻을 때, 해당 novelBoard 는 어떻게 될까.
  - TODO maxStageSize 가 일정값 이상이면 throw? => 소설이 완성되는 것은 언제인가. close
  - 하나의 novelBoard 는 언제 끝나는가( close 시점 ) ?
- 소설 뿐만 아니라 '시'도 릴레이가 가능하다면? NovelBoard 를 추상화시켜야한다. (-> RelayBoard)
- 릴레이 노벨롭먼트의 바이브 - 재미냐? 퀄리티냐?
- article 의 저작권은 저자에게 있는가? 노벨롭먼트에게 있는가?
  - article 의 수정 권한은 누구에게 있는가?
  - article 의 삭제 권한은 누구에게 있는가?
  - article 의 수정은 언제 가능한가 - 자식 relay 가 없을 때
  - article 의 삭제는 언제 가능한가 - 자식 relay 가 없을 때
- user 에게 자유권을 얼마나 줄 것인가 
- user 가 창작한 글에 대한 제한을 얼마나 둘 것인가

### 기타 
- 1차 배포 시점 정하기
- 1차 배포에서 다음을 포함할 것인가
  - 로그인 / 회원가입
  - close 기능
  - 수정 / 삭제 기능


### TODO
[ADMIN]
- 릴레이 승인 요청 api 정의 및 구현
- 릴레이 수정 요청 api 정의 및 구현
- 릴레이 보드 close 구현
- 화면 구현

[USER]
- 회원가입 구현
- 로그인 구현
- 화면 구현

![](../../../../../../Desktop/2022-11-24_19-14-31ddddd.jpeg)