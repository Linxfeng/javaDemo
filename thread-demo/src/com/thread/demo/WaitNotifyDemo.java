package com.thread.demo;

/**
 * 多线程唤醒demo
 *
 * @author lintao
 * @date 2020/7/5
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {

        Object lock = new Object();

        ThreadA threadA = new ThreadA(lock);
        //获取锁，wait
        threadA.start();

        //获取锁，wait
        new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("start Thread");
                    //threadA.join();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end Thread");
            }
        }).start();

        ThreadB threadB = new ThreadB(lock);
        //获取锁，唤醒，释放锁
        threadB.start();
    }
}
