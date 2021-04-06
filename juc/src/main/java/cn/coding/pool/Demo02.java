package cn.coding.pool;
import java.util.concurrent.*;

public class Demo02 {
    public static void main(String[] args) {

        // 自定义线程池，7个参数
        // 最大线程到底该如何定义
        // 1、CPU 密集型，几核，就是几，可以保持CPU的效率最高
        // 2、IO 密集型，判断程序中十分耗IO的线程，

        // 获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = new ThreadPoolExecutor(
                2,// 核心线程池大小
                5,// 最大的线程池数量
                3,// 超时等待
                TimeUnit.SECONDS,// 秒为单位
                new LinkedBlockingDeque<>(3),// 阻塞队列
                Executors.defaultThreadFactory(),// 线程工厂
                new ThreadPoolExecutor.DiscardOldestPolicy());// 拒绝策略，满了，不处理继续进来的，抛出异常

        /**
         * 四种拒绝策略
         * 1、AbortPolicy直接抛出异常
         * 2、CallerRunsPolicy哪条线程来的，让哪条线程去执行
         * 3、DiscardPolicy队列满了，不会抛出异常，会丢掉任务
         * 4、DiscardOldestPolicy队列满了，尝试去和最早的线程竞争，如果失败，丢掉该任务，成功则执行
         */

        try {
            // 最大承载=阻塞队列大小+最大线程池数量
            for (int i = 1; i <= 9; i++) {
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
