package cn.coding.pattern.observer.weather;

/**
 * 主题，观察者通过订阅主题对象
 */
public interface Subject {
    /**
     * 注册一个观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 删除一个观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者
     */
    void notifyObservers();
}
