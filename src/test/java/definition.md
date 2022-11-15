### 용어 정의
- `NovelBoard` : 소설 릴레이를 위한 기본 대시보드.
- `Opening` : 어드민이 제공하는 NovelBoard 의 첫머리. 
- `Stage` : Opening 에 이어 릴레이 될 Stage.     
  - StageState 이라는 상태를 가지며 상태마다 Fork 될 크기가 지정되어 있음.
  - `StageState` : BEGINNING, RISING_ACTION, CRISIS, CLIMAX, ENDING
- `Fork` : 각 Stage 마다 생기는 이야기 갈래.



### 기술 정의 
- NovelBoard 에 Stage 를 ( hold ) 할 수 있다.
  - init() 과 add() 가 있다. 
  - 어떤 Stage 인 줄 알 것 인가?
  - 각 Stage 에 마지막 closeFork 가 있다면 다음 Stage 로?
- Opening 은 NovelBoard 생성시 함께 생성된다.
- Stage 에 fork 를 ( relay ) 할 수 있다.
  - init()  과 add() 가 있다.
- 각 Stage 는 NovelBoard 의 단계를 상태로 가진다.
- 각 Stage 는 fork 가 실릴 수 있는 갯수가 정해져있다.
  ![](../../../../../../Library/Containers/com.majimakHARU.GrabIt/Data/2022-11-15_22-06-31.png)