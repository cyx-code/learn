package cn.coding.pattern.strategy.impl;

import cn.coding.pattern.strategy.Duck;

/**
 * 不同品种的鸭子
 */
public class MallardDuck extends Duck {
    @Override
    public void display() {
        System.out.println("绿头鸭");
    }

}
