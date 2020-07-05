package com.thread.demo;

/**
 * 同步demo
 *
 * @author lintao
 * @date 2020/7/5
 */
public class SyncDemo implements Runnable {

    private static volatile boolean isRunning = false; //中断标识

    private Object lock;

    public static void main(String[] args) {
        SyncDemo sd = new SyncDemo();

        new Thread(sd::demo).start();

        new Thread(sd::demo).start();

        new Thread().interrupt();

        System.out.println("--end main--");
    }

    @Override
    public void run() {
        System.out.println("run start, isRunning=" + isRunning);
        while (!isRunning) {
            System.out.println("I am isRunning...");
        }
        System.out.println("run end, isRunning=" + isRunning);
    }

    public void demo() {
        try {
            synchronized (this) {
                System.out.println("--start--");

                //线程睡眠，让出资源，不释放锁
                Thread.sleep(0);

                //线程让步，让出资源，不释放锁
                Thread.yield();

                System.out.println("--end--");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
