package com.til.java.udemy.thread.section2;

import java.util.ArrayList;
import java.util.List;

public class Section2 {
    public static void main(String[] args) throws InterruptedException {
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

        Thread.sleep(100);
        System.out.println("===========handle-exception===========");

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

        Thread.sleep(100);
        System.out.println("===========multi-thread===========");

        List<Runnable> tasks = new ArrayList<>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("multi-thread - 0");
            }
        };
        tasks.add(runnable);
        tasks.add(() -> System.out.println("multi-thread - 1"));
        tasks.add(() -> System.out.println("multi-thread - 2"));
        tasks.add(() -> System.out.println("multi-thread - 3"));
        MultiExecutor multiExecutor = new MultiExecutor(tasks);
        multiExecutor.executeAll();
    }
}
