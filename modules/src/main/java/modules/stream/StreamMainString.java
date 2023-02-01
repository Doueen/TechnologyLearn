package modules.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangHongzheng @Description
 * @create 2022-11-04 17:21
 */
public class StreamMainString {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("zhz");
        list.add("zhz44");
        list.add("spj4");
        list.add("zwt4");
        list.add("zy553");
        list.add("z");
        list.add("zyt445");

        List list1 =
                list.stream()
                        // 去重
                        .distinct()
                        // 筛选
                        .filter(name -> name.startsWith("z"))
                        // 映射 操作元素进行覆盖,不会改变源对象
                        .map(name -> name += "_name")
                        // 排序
                        .sorted((n1, n2) -> n1.length() - n2.length())
                        // 跳过一个
                        .skip(1)
                        // 设置最大长度
                        .limit(4)
                        // 终结：遍历
                        // .forEach(System.out::println);
                        // 终结：计数
                        //  .count();
                        // 终结：最大
                        //  .max((n1, n2) -> n1.length() - n2.length());
                        // 终结：转化为集合
                        .collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list);
    }
}
