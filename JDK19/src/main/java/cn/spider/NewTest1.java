package cn.spider;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 新特性一:集合
 */
public class NewTest1 {

    public static void main(String[] args) {
        List<String> list = List.of("a", "b", "a", "C");
        System.out.println(list);//[a, b, a, C]
        // list.add("w");//UnsupportedOperationException 不支持操作异常
        //原因：使用了of()方法，就不能再添加了，和数组一样了，长度固定
        System.out.println("-----------------------------");

        //Set<String> set = Set.of("a", "b", "d", "d");
        Set<String> set = Set.of("a", "b", "d");
        System.out.println(set);//[b, a, d] IllegalArgumentException 非法参数异常
        //原因：set集合中不允许出现重复元素，因此of()方法传参也是要唯一的。
        // set.add("w");//UnsupportedOperationException 不支持操作异常
        System.out.println("-----------------------------");

        //Map<String, Integer> map = Map.of("张三", 185, "李四", 168, "王五", 175,"张三",168);
        Map<String, Integer> map = Map.of("张三", 185, "李四", 168, "王五", 175);
        //IllegalArgumentException 非法参数异常
        System.out.println(map);//{李四=168, 王五=175, 张三=185}
        // map.put("赵六",200);//UnsupportedOperationException 不支持操作异常

    }
}
