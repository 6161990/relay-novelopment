### 용어 정의
- `NovelBoard` : 소설 릴레이를 위한 기본 대시보드.
- `Opening` : 어드민이 제공하는 NovelBoard 의 첫머리. 
- `Novel` : Opening 에 이어 릴레이 된 소설.
- `Fork` : 각 릴레이마다 생기는 이야기 갈래.


### 기술 정의
- Opening 은 NovelBoard 생성시 함께 생성된다.
- NovelBoard 에 Novel 을 ( relay ) 할 수 있다.
- NovelBoard 에 Novel 을 ( fork ) 할 수 있다.
- relay 된 Novel 은 부모 NovelId를 알고 있다.
- fork 된 Novel 은 같은 부모 NovelId을 공유하는 형제 Novel 이 있다.
- 부모 NovelId는 뭘로 이루어질 것인가. view 에 뿌려질 순서를 id 로 알 수 있을까. 📌 중요한듯..
- maxRelaySize 가 찻을 때, 해당 novelBoard 는 어떻게 될까. 
  - TODO maxStageSize 가 일정값 이상이면 throw = 소설이 완성되는 것은 언제인가.
- 각 idGenerator 를 뭘로 만들까.
  - novelId는 novelKey 로 만들까 (novelBoardId, writer 의 조합? title 의 조합?)
  

### IDEA 
* 유저에 따라 Stage 를 추가시킬 수 있다. 등급이 높으면 이미 10개의 Stage 가 달려있는 novelBoard 에 추가할 수 있다.   
* 노벨상.................. 띵언 노벨 판타지상 노벨 환희상 등등
* relay 말고 append 기능은 어떨까 글자수가 부담스러우니까. 두 세 줄만 append
* append ?  기존 소설의 맨 끝자리에 넘어온 문장에 덧붙인다. 


![](../../../../../../Desktop/2022-11-24_19-14-31ddddd.jpeg)