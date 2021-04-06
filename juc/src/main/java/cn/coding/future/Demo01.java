package cn.coding.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用CompletableFuture
 *
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*// 没有返回值的runAsync 异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync:void");
        });
        System.out.println("1111");
        completableFuture.get();// 阻塞获取执行结果*/

        // 有返回值的异步回调
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync:Integer");
            int i = 10 / 0;
            return 1024;
        });
        // T是正常的返回结果
        // U是错误信息
        System.out.println(completableFuture1.whenComplete((t, u) -> {
            System.out.println("T:" + t + ",U:" + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233;// 可以获得错误的返回结果
        }).get());
    }
}
