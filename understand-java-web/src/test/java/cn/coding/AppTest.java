package cn.coding;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1212; i++) {
            list.add("list" + i);
        }
        List<List<String>> lists = splitList(list, 50);
        System.out.println(lists.size());
        lists.forEach(list1 ->  {
            list1.forEach(list11 -> {
                System.out.print(list11 + ",");
            });
            System.out.println("-------------");
        });
        System.out.println(Void.TYPE);
    }
    public List<List<String>> splitList(List<String> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<String>> result = new ArrayList<List<String>>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<String> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
