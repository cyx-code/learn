package cn.coding.pattern.decorator.pizza;

public class Cheese extends ToppingDecorator {

    public Cheese(Pizza pizza) {
        this.pizza = pizza;
    }
    @Override
    public String getDescription() {
        return pizza.getDescription() + "cheese";
    }

    @Override
    public double cost() {
        return pizza.cost();// cheese是免费的
    }
}
