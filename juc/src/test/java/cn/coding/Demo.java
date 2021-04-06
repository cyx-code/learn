package cn.coding;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo {
    /**
     * 自定义线程池
     */
    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            4, // 核心线程
            8, // 最大线程
            3, // 超时等待
            TimeUnit.SECONDS, // 时间单位
            new LinkedBlockingDeque<Runnable>(4), // 阻塞队列
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy()); // 拒绝策略：尝试和第一个线程竞争，成功则执行否则拒绝
    @Test
    public void test1() {
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(111); }
        });
    }

    @Test
    public void name() {
        int a = 2;
        int b = 4;
        System.out.println("a:" + b);
        System.out.println("b:" + a);
    }
}
