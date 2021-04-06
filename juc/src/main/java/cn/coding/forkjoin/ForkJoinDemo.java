package cn.coding.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务
 * 如何使用ForkJoin
 * 1、通过ForkjoinPool执行
 * 2、计算任务forkjoinPool.execute(ForkJoinTask task)
 * 3、
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    // 临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    // 计算方法
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {// forkjoin
            long middle = start + (end - start) >> 1;// 中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();// 拆分任务，将任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
