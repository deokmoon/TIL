# Section3 - 스레드 조정(thread coordination)

## 스레드 종료 및 데몬 스레드
### 스레드 종료 방법
- Interrupt
  - Interrupt 하려는 스레드가 InterruptException이 발생하는 경우
  - 스레드가 Interrupt를 명시적으로 처리하는 경우 
- Daemon
  - main 스레드가 멈추면 애플리케이션 종료를 막지 않는다 -> 애플리케이션을 종료하는데 방해하지 않는 Thread로 만든다
  - 앱의 백그라운드 작업을 맡음 -> intellij의 오토 save 기능과 같이?
  - 스레드 인터럽트를 정상적으로 처리할 필요가 없다면, Daemon 속성을 true로 하면 됨
  - 예시로 긴 연산을 기다리지 않고 main이 멈추면 데몬 스레드를 기다리지 않고 애플리케이션이 종료됨

연산이 안끝나도 main 스레드가 종료되면 전체 앱이 종료됨
### 스레드 종료가 필요한 이유
- 스레드는 아무것도 안할 때 리소스를 사용 함 
  - 일부 커널 리소스
  - CPU 시간
  - CPU 캐시
  - 긴 연산
  - 응답없는 웹페이지 기다리는 경우
  -> 리소스를 정리가 필요
- 정지하는 이유는 애플리케이션을 중단하기 위해!