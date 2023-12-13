package com.til.java.thread.task;

public class Task extends Thread {

    private final int number;

    public Task(int number) {
        this.number = number;
    }

    public void run() {
        System.out.println("\nTask" + number + " started");
        for (int i = 100 * number; i <= number * 100 + 99; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\nTask" + number + " Done");
    }
}
