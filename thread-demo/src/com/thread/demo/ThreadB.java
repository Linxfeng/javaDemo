package com.thread.demo;

/**
 * 多线程唤醒demo-ThreadB
 *
 * @author lintao
 * @date 2020/7/5
 */
public class ThreadB extends Thread {

    private final Object lock;

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("start ThreadB");
            lock.notifyAll(); //唤醒被阻塞的线程
            System.out.println("end ThreadB");
        }
    }

}
