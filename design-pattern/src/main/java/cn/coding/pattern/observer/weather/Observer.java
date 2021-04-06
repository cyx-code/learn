package cn.coding.pattern.observer.weather;

/**
 * 观察者接口
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
