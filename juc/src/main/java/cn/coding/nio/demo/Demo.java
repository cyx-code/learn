package cn.coding.nio.demo;

import java.util.concurrent.*;

/**
 * @description: Demo
 * @author: cyx
 * @create: 2020/08/11
 */
public class Demo implements Callable<String> {

    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> task = new FutureTask<String>(new Demo());
        executor.submit(task);
        try {
            // get方法会阻塞当前线程，直到得到结果。
            System.out.println(task.get());
            System.out.println(task.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String call() throws Exception {
        return "返回值";
    }
}
