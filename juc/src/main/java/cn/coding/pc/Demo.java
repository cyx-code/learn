package cn.coding.pc;

// synchronized实现生产者消费者问题
public class Demo {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }
}

class Data {
    private int number = 0;


    public synchronized void increment() throws InterruptedException {
        // 当只有两个线程时，if没有问题，在当超过两个线程后，将会出现虚假唤醒问题
//        if (number != 0) {
//            // 等待
//            this.wait();
//        }
        // wait方法应该放在循环内，为解决虚假唤醒问题
        while (number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程，+1完毕
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
//        if (number == 0) {
//            // 等待
//            this.wait();
//        }
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程，-1完毕
        this.notifyAll();
    }
}
