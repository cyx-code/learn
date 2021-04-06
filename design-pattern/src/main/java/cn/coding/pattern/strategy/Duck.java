package cn.coding.pattern.strategy;

import cn.coding.pattern.strategy.impl.FlyWithWings;
import cn.coding.pattern.strategy.impl.Quack;

/**
 * 鸭子抽象类
 */

public abstract class Duck {
    // 鸭子行为（算法族）
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    // 定义默认行为方法
    public Duck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    /**
     * 所有鸭子的共有方法
     */
    public void swim() {
        System.out.println("游泳");
    }

    /**
     * 每个鸭子的形态由自己实现
     */
    public abstract void display();
    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuck() {
        quackBehavior.quack();
    }
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
