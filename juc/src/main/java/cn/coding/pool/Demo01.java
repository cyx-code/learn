package cn.coding.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors 工具类，三大方法
 */
public class Demo01 {

    public static Map m = new HashMap() {{
        m.put("a", "2");
    }};
    public static void main(String[] args) {

        Integer isInt = (Integer) m.get("a");
        System.out.println(isInt);
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        /*ExecutorService threadPool1 = Executors.newSingleThreadExecutor();// 单个线程
        ExecutorService threadPool2 = Executors.newFixedThreadPool(5);// 创建一个固定大小的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();// 可伸缩的线程池

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + ":ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }*/
    }
}
