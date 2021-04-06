package cn.coding.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();// 268
        test3();// 165
    }

    public static void test1() {
        long sum = 0;
        long start = System.currentTimeMillis();
        for (long i = 0; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间：" + (end - start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long sum = 0;
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0l, 10_0000_0000l);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间：" + (end - start));
    }

    public static void test3() {
        long sum = 0;
        long start = System.currentTimeMillis();

        sum = LongStream.rangeClosed(0l, 10_0000_0000l).parallel().reduce(0, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间：" + (end - start));
    }
}
