package cn.coding.threadloacl;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @description: ThreadLoacl的demo
 * @author: cyx
 * @create: 2020/08/31
 */
public class ThreadLocalDemo implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    );
    @Override
    public void run() {
        System.out.println("Thread Name="+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won'treflect to other threads
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo demo = new ThreadLocalDemo();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(demo, "Thread" + i);
            t.sleep(1000);
            t.start();
        }
    }
}
