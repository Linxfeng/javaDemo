package com.thread.demo;

/**
 * 多线程唤醒demo-ThreadA
 *
 * @author lintao
 * @date 2020/7/5
 */
public class ThreadA extends Thread {

    private final Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("start ThreadA");
            try {
                lock.wait(); //实现线程的阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end ThreadA");
        }
    }
}
