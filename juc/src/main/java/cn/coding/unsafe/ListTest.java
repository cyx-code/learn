package cn.coding.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * java.util.ConcurrentModificationException 并发修改异常
 */
public class ListTest {
    public static void main(String[] args) {
        /**
         * List<String> list = new ArrayList<>();并发场景下不安全
         * List<String> list = new Vector<>();add方法是synchronized
         * List<String> list = Collections.synchronizedList(new ArrayList<>());通过工具类使List安全
         *
         * List<String> list = new CopyOnWriteArrayList<>();
         * CopyOnWrite 写入时复制
         *
         * CopyOnWriteArrayList的add方法的源码
         * final ReentrantLock lock = this.lock;
         * lock.lock();// 加锁
         * try {
         *     Object[] elements = getArray();
         *     int len = elements.length;
         *     Object[] newElements = Arrays.copyOf(elements, len + 1);// 在写入时，将值复制出来
         *     newElements[len] = e;
         *     setArray(newElements);// 再写入
         *     return true;
         * } finally {
         *     lock.unlock();
         * }
         *
         */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
