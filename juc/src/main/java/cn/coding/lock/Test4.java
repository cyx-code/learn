package cn.coding.lock;

import java.util.concurrent.TimeUnit;

public class Test4 {

    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone4 {

    // static synchronized 锁的是类
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    // 普通的同步方法 锁的是调用者
    public synchronized void call() {
        System.out.println("打电话");
    }

}
