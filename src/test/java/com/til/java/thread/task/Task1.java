package com.til.java.thread.task;

public class Task1 extends Thread{

    public void run() {
        System.out.println("\nTask1 started");
        for (int i = 101; i <= 199; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\nTask1 Done");
    }
}
