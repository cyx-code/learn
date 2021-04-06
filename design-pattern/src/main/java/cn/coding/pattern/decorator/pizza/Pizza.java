package cn.coding.pattern.decorator.pizza;

public abstract class Pizza {
    private String description = "Basic Pizza";
    public String getDescription() {
        return description;
    }
    public abstract double cost();
}
