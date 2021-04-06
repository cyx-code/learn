package cn.coding.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四个函数式接口
 */
public class Demo01 {
    public static void main(String[] args) {
        // 函数式接口, 可以定义入参和返参类型
        Function<String, String> fun = str -> { return str; };
        // 等价于
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };
        System.out.println(fun.apply("aaa"));


        // 断定型接口，有一个输入参数，返回值只能是bool类型
//        Predicate<String> predicate = new Predicate<String>() {
//
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };
        Predicate<String> predicate = str -> { return str.isEmpty(); };
        System.out.println(predicate.test(""));

        // 消费型接口，只有入参，没有返回值
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
        Consumer<String> consumer = str -> {
            System.out.println(str);
        };
        consumer.accept("consumer");

        //  供给型接口，没有参数，只有返回值
//        Supplier<Integer> supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                System.out.println("get()");
//                return 1024;
//            }
//        };
        Supplier<Integer> supplier = () -> {
            System.out.println("get()");
            return 1024;
        };
        System.out.println(supplier.get());
    }
}
