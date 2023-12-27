package com.til.java.udemy.thread.Section3;

import java.math.BigInteger;

public class Section3 {

    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread(new BlockingTask());
        thread.start();
        thread.interrupt(); // blocking thread를 Interrupt Exception 이 발생 후에 애플리케이션이 멈춤

        // interrupt
        Thread.sleep(100);
        Thread longTimeThread = new Thread(new LongComputeTask(new BigInteger("10000000"), new BigInteger("500000000")));
        longTimeThread.start();

        Thread.sleep(2000);
        System.out.println("Interrupt start");
        longTimeThread.interrupt();

        // daemon
        Thread.sleep(2000);
        Thread daemon = new Thread(new DaemonThread(new BigInteger("10000000"), new BigInteger("500000000")));
        daemon.setDaemon(true);
        daemon.start();
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Exiting Blocking Queue");
            }
        }
    }

    private static class LongComputeTask implements Runnable {

        private BigInteger base;
        private BigInteger power;

        public LongComputeTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + "=" + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            // 오래걸리는 spot 찾기
            for (BigInteger i = BigInteger.ONE; i.compareTo(power) != 0; i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
