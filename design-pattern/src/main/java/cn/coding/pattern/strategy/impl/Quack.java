package cn.coding.pattern.strategy.impl;

import cn.coding.pattern.strategy.QuackBehavior;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
