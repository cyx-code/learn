package cn.coding.pattern.strategy.impl;

import cn.coding.pattern.strategy.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}
