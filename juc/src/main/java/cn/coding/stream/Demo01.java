package cn.coding.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 筛选:
 * ID:偶数
 * 年龄大于11
 *
 */
public class Demo01 {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 10);
        User u2 = new User(2, "b", 11);
        User u3 = new User(3, "c", 12);
        User u4 = new User(4, "d", 13);
        User u5 = new User(5, "e", 14);

        List<User> users = Arrays.asList(u1, u2, u3, u4, u5);
        Stream<User> stream = users.stream();

        /**
         * 链式编程
         * filter方法，根据条件筛选数据，然后返回
         * map方法，对集合进行操作后返回
         * sorted方法，按照指定的排序规则排序
         * limit返回前几个结果
         */
        stream.filter(u -> {
            return u.getId() % 2 != 0;
        }).filter(u -> {
            return u.getAge() > 11;
        }).map(u -> {
            return u.getName().toUpperCase();
        }).sorted((uu1, uu2) -> {
            return uu2.compareTo(uu1);
        }).limit(1).forEach(System.out::println);
    }
}
