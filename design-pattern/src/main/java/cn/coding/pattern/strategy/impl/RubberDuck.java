package cn.coding.pattern.strategy.impl;

import cn.coding.pattern.strategy.Duck;

public class RubberDuck extends Duck {
    @Override
    public void display() {
        System.out.println("橡皮鸭");
    }
}
