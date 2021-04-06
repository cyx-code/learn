package cn.coding.tvolatile;

import java.util.concurrent.TimeUnit;

public class Demo {
    /**
     * 1、保证可见性
     * 2、不保证原子性
     */
    private volatile static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0) {// 如果num没有volatile修饰，该线程将死循环

            }
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num = 1);
    }
}
