package cn.coding.lock;

import java.util.concurrent.TimeUnit;

public class Test3 {

    public static void main(String[] args) {
        Phone3 phone = new Phone3();
        Phone3 phone3 = new Phone3();
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone3.call();
        }, "B").start();
    }
}

class Phone3 {

    // static synchronized 锁的是类
    public static synchronized void sendSms() {

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }


}
