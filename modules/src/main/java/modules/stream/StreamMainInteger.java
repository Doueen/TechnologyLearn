package modules.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangHongzheng @Description 不要惰性求值（如果没有终结操作，中间操作是不会得到执行的）
 *     流是一次性的（一旦一个流对象经过一个终结操作后，这个流就不能再被使用） 不会影响原数据（我们在流中可以对数据做很多处理，不会对原数据有影响）
 * @create 2022-11-04 17:53
 */
public class StreamMainInteger {
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(0);
    list.add(25);
    list.add(85);
    list.add(35);
    list.add(25);
    list.add(86);
    list.add(96);
    list.add(96);

    int nu =
        list.stream()
            // 去重
            .distinct()
            // 筛选
            .filter(name -> name > 1)
            // 映射 操作元素进行覆盖
            .map(name -> name + 509)
            // 排序
            .sorted((n1, n2) -> n1 - n2)
            // 跳过一个
            .skip(1)
            // 设置最大长度
            .limit(2)
            // 终结：遍历
            // .forEach(System.out::println);
            // 终结：计数
            //  .count();
            // 终结：最大
            //  .max((n1, n2) -> n1.length() - n2.length());
            // 终结：转化为集合
            //  .collect(Collectors.toList());
            // 终结：匹配 还包括 allMatch；noneMatch()
            // .anyMatch(item->item>50);
            // 终结：查找  第一个 还有 findFirst
            // .findAny();
            // 终结：reduce 的作用就是把 stream
            // 流中的元素组合起来，我们可以传入一个初始值，它会按照我们的计算方式依次拿流中的元素和初始值进行计算后返回结果，结果再和后面的元素计算（累加）。
            .reduce(0, (res, num) -> num - res);
    System.out.println(nu);
    System.out.println(list);
  }
}
