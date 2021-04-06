package cn.coding.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket02 ticket = new Ticket02();

        new Thread(() -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();

    }
}

/**
 * 通过lock锁完成多线程安全
 */
class Ticket02 {
    private int number = 50;
    Lock lock = new ReentrantLock();

    public synchronized void sale() {
        lock.lock();// 加锁
        lock.tryLock();// 尝试获取锁
        try {
            // 业务代码
            if (number > 0) {
                System.out.println("卖出了" + (50 - --number) + "张票，还剩" + number + "张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();// 解锁
        }

    }
}