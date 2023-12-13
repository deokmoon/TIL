package com.til.java.thread.task;

public class Task2 implements Runnable{

    public void run() {
        System.out.println("\nTask2 started");
        for (int i = 201; i <= 299; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\nTask2 Done");
    }
}
