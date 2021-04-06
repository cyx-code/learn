package cn.coding;

import java.util.BitSet;

/**
 * 自定义布隆过滤器
 */
public class MyBloomFilter {
    // 位数组大小
    private static final Integer DEFAULT_SIZE = 2 << 24;
    // 多个hash函数
    private static final int[] SEEDS = {3, 13, 46, 71, 91, 134};
    // 位数组
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    // 存放哈希函数的数组
    private SimpleHash[] func = new SimpleHash[SEEDS.length];
    // 构造器，初始化hash函数
    public MyBloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            // 初始化多个hash函数
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }
    // 向位数组中插入数据
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }
    // 判断指定元素是否存在
    public boolean contains(Object value) {
        boolean res = true;
        for (SimpleHash f : func) {
            res = res && bits.get(f.hash(value));
        }
        return res;
    }

    public static class SimpleHash {
        private int cap;
        private int seed;
        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }
        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }

    public static void main(String[] args) {
        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        MyBloomFilter filter = new MyBloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
    }

}
