package cn.coding.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不保证原子性
 *
 * 使用原子类可以解决原子性问题
 */
public class Demo02 {

    private volatile static int num = 0;
//    private volatile static AtomicInteger num = new AtomicInteger();
    public static void add() {
//        num++;// 不是原子性操作
//        num.getAndIncrement();// +1方法，CAS操作

    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {// java中有两个默认线程，main,gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
