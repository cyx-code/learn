package cn.coding;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 4;
        Demo demo = new Demo();
        System.out.println(Arrays.toString(demo.twoSum(arr, target)));
    }

    public int[] twoSum(int[] array, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> help = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (help.containsKey(target - array[i])) {
                res[0] = help.get(target - array[i]);
                res[1] = i;
                return res;
            }
            help.put(array[i], i);
        }
        return null;
    }
}
