package com.til.java.thread.task;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

    private final String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Hello " + name;
    }
}
