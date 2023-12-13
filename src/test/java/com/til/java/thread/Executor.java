package com.til.java.thread;

import com.til.java.thread.task.CallableTask;
import com.til.java.thread.task.Task;
import com.til.java.thread.task.Task1;
import com.til.java.thread.task.Task2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor {

    @Test
    final void singleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Task1());
        executorService.execute(new Thread(new Task2()));

        System.out.println("\nTask3 kick off");
        for (int i = 301; i <= 399; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\nTask3 Done");
        System.out.print("\nMain Done");
        executorService.shutdown();
    }

    @Test
    final void multiThreadTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(new Task(1));
        executorService.execute(new Task(2));
        executorService.execute(new Task(3));
        executorService.execute(new Task(4));
        executorService.execute(new Task(5));
        executorService.execute(new Task(6));

        executorService.shutdown();
    }

    @Test
    final void callableTest() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> welcomeFuture = executorService.submit(new CallableTask("in28Minutes"));
        System.out.print("\n new CallableTask in28Minutes executed");

        String welcomeMessage = welcomeFuture.get();
        System.out.print("\n==== welcomeMessage: " + welcomeMessage);
        System.out.print("\n main completed");
        executorService.shutdown();
    }

    @Test
    final void multiCallableTest() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<CallableTask> tasks = List.of(new CallableTask("cheongju"), new CallableTask("ulsan"), new CallableTask("seoul"));
        List<Future<String>> results = executorService.invokeAll(tasks);

        for (Future<String> result : results) {
            System.out.println(result.get());
        }

        executorService.shutdown();
    }

    @Test
    final void multiAnyCallableTest() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<CallableTask> tasks = List.of(new CallableTask("cheongju"), new CallableTask("ulsan"), new CallableTask("seoul"));
        String result = executorService.invokeAny(tasks);

        System.out.println(result);

        executorService.shutdown();
    }
}
