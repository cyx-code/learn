package cn.coding.callable;

import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread myThread = new MyThread();

        FutureTask<Integer> futureTask = new FutureTask<>(myThread);

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();// 结果有缓存，结果可能需要等待，会阻塞

        Integer result = futureTask.get();// 这个get方法可能会产生阻塞，放到最后执行
        // 或者通过异步通信处理
        System.out.println(result);


        // CountDownLatch 线程减法计数器，CyclicBarrier 线程加法计数器
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐了");
        });


        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println("收集了" + temp + "个");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return 1024;
    }
}
