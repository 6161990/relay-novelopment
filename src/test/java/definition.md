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
- 
  
### IDEA 
* 유저에 따라 Stage 를 추가시킬 수 있다. 등급이 높으면 이미 10개의 Stage 가 달려있는 novelBoard 에 추가할 수 있다.   
* 노벨상.................. 띵언 노벨 판타지상 노벨 환희상 등등

![](../../../../../../Library/Containers/com.majimakHARU.GrabIt/Data/2022-11-15_22-06-31.png)