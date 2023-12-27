# Section2 스레드 기초

## 전체 소스
- [Main Code](Section2.java)
- [MultiExecutor](MultiExecutor.java)

## 생성 및 우선순위 설정
### 기본 생성
~~~ java
public class Main {
    public static void main(String [] args) {
        Thread thread = new TaskThread1();
        thread.start();        
    }
    
    public static class TaskThread1 extends Thread {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }
}

public class Main {
    public static void main(String [] args) {
        Thread thread = new Thread(new Task2());
        thread.start();
    }
 
    public static class Task2 implements Runnable {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }
}

~~~
### 실습
~~~ java
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are now in thread: " + Thread.currentThread().getName());
                System.out.println("Current thread priority is: " + Thread.currentThread().getPriority());
            }
        });
        thread.setName("New worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread() + " after starting a new thread");
~~~

## 스레드 예외 처리 및 로깅?
~~~ java
    Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            throw new RuntimeException("Intentional Exception");
        }
    });

    thread1.setName("Misbehaving thread");
    thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("A Critical error happened in thread: " + t.getName() + " error is " + e.getMessage());
        }
    });
    thread1.start();
~~~
- setUncaughtExceptionHandler 를 상속받아 재정의하여 예외 처리도 가능하다.
~~~java
class MyHandler implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t.getName() + " occurred exception");
		//e.printStackTrace();
	}
}
~~~

## Multi-Executor
- 멀티코어 CPU를 최대한 활용하기 위해, 우리는 각 작업을 서로 다른 스레드로 전달해서 MultiExecutor가 모든 작업을 동시에 진행
~~~ java
public class MultiExecutor {
    private final List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        List<Thread> threads = new ArrayList<>(tasks.size());

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        for(Thread thread : threads) {
            thread.start();
        }
    }
}
~~~