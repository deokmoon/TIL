package com.til.java.udemy.thread.Section3;

import java.math.BigInteger;

public class DaemonThread implements Runnable {

    private BigInteger base;
    private BigInteger power;

    public DaemonThread(BigInteger base, BigInteger power) {
        this.base = base;
        this.power = power;
    }

    @Override
    public void run() {
        System.out.println("");
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
