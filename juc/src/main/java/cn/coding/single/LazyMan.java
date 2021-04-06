package cn.coding.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式单例
 */
public class LazyMan {

    private LazyMan() {}
    private volatile static LazyMan lazyMan;

    // 双重检测锁模式的懒汉式单例 DCL懒汉式
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();// 不是原子性操作
                }
            }
        }
        return lazyMan;
    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        for (int i = 1; i <= 10; i++) {
//            new Thread(() -> {
//                System.out.println(LazyMan.getInstance());
//            }).start();
//        }

        // 通过反射破坏单例
        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);// 无视私有
        LazyMan lazyMan = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(lazyMan);
    }
}
