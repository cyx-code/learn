package cn.coding.callable;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    public static void main(String[] args) {
        // 线程数量， 限流
        // 用于多个共享资源互斥的使用！   并发限流，控制最大的线程数
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    // 获得资源
                    semaphore.acquire();// 获得线程资源，如果满了，等待，直到有资源被释放为止
                    System.out.println(Thread.currentThread().getName() + "抢占资源");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "释放资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放资源
                    semaphore.release();// 释放线程资源，会将当前的信号量+1，然后唤醒等待的线程
                }
            }, String.valueOf(i)).start();
        }
    }
}
