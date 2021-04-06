package cn.coding.pattern.strategy.impl;

import cn.coding.pattern.strategy.FlyBehavior;

/**
 * 具体的行为实现类（具体算法实现）
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("用翅膀飞");
    }
}
