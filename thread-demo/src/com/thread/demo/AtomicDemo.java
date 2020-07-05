package com.thread.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 原子操作-加锁demo
 *
 * @author lintao
 * @date 2020/7/5
 */
public class AtomicDemo {

    private static int count = 0;//正确性
    private static Lock lock = new ReentrantLock();//Lock

    public /*synchronized*/ static void incr() {
        synchronized (AtomicDemo.class) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    public static void incr1() {
        lock.lock();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::incr).start();
            //new Thread(AtomicDemo::incr1).start();
        }
        Thread.sleep(5000);
        System.out.println("运行结果：" + count);

    }

}
