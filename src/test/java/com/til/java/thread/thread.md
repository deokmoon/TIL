# Thread
---
## 쓰레드 상태

- New - 말 그대로 new Thread 할 때
- Runnable
- Running
- Blocked / Waiting
- Terminated / Dead - Thread가 완료될 때 done 상태

- 위와 같은 상태가 있는데 아래와 같이 Task1 / Task2 / Task3 는 각 Task의 넘버에 해당하는 X00 숫자를 출력하고 있는 프로그램이 있다.
- Task1이 있고 158을 찍고 / Task2가 201을 찍고 있을 때  Task2는 Running / Task1는 Runnable 상태이다.
- Blocked
    - Thread3 는 Thread 1의 데이터에 영향을 받음
    - Thread3 는 Thread 1의 실행이 완료되도록 기다림 → blocked
    - 외부 인터페이스나 데이터베이스등으로부터 어떤 입력을 대기
    - 실행이 완료되지 않은 Thread로 부터 데이터를 입력 받아야 하는 상황

## 쓰레드 우선순위

- Thread 클래스에 serPriority 메서드를 활용 min: 1, norm: 5, max: 10
- 우선순위는 추천순위 정도이지 무조건이 아니다
- 항상 max(10) 인 스레드를 처리하는 것은 아니다

## 쓰레드와 소통 - join 메서드

- task1.join() 걸면 해당 라인에서 task1이 끝날 때까지 기다린다.

### 스레드 활용 메서드 / 동시성 키워드 - sleep, yield

- yield cpu 할당을 양보를 한다?
- synchronized - hashtable이 왜 동기화가 되어야 하는지?
    - Task1 , Task2 둘 중 하나만 Synchronized 선언된 메서드를 수행할 수 있다
    - 동기화에 따른 overhead 가 많이 생긴다
    - → 더 안전하게 동시성을 다루는 방법이 java 5 / 8에 많이 생겼다 → concurrent collection

### Executor 서비스 → 스레드의 실행 제어

- 수행한 스레드를 반환하거나 실행 순서 제어등의 기능이 필요

### Callable Test

- Future 객체로 받아서 get을 하면 해당 스레드의 수행이 완료될떄까지 대기

---
### Udemy - best-java-programming 강의 참조