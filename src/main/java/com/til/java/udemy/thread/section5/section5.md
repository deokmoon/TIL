# Section 5 스레드 간 데이터 공유
## 스택 및 메모리 영역

### 스택
- 메서드 수행 영역
- 각 스레드에 배타적
- 로컬 변수, 로컬 레퍼런스
- 선입 후출 -> a 메서드에서 b 메서드를 호출한다면 b 메서드 Frame(영역) 이 스택에 생성됨
  - b가 완료되어야지 a가 완료 -> 선입 후출 -> 왜 Stack 영역인지 알 수 있음

### 메모리(Heap)
- 객체는 레퍼런스가 하나라도 있으면 힙에 머무르고 없으면 GC 가 수거함 
- Objects
- class members
- static variables

## 리소스 공유와 임계영역 개요
### 리소스
- 변수 / 자료구조(etc: map) / File Connection / Message 등
### 스레드 간의 자료 공유가 필요한 이유
- work dispatcher(single) -> work queue -> worker thread(multi) 인 경우 큐를 공유함으로써 요청을 처리할 수 있다는 장점
- DB 연결에 대한 Database Microservice 스레드 간의 공유가 이뤄져야지 멀티 스레드에 대한 관리가 가능
### 임계영역이 필요한 이유에 대해 설명
- 