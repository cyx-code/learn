package cn.coding.single;

/**
 * 饿汉式
 */
public class Hungry {


    private Hungry() {

    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
